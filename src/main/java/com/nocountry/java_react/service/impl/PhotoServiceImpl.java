package com.nocountry.java_react.service.impl;

import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.mapper.PhotoMapper;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements IPhotoService {

    private final IPhotoRepository repository;

    private final PhotoMapper mapper;

    @Override
    @Transactional
    public void init(Path pathFolderUpload) {
        try {
            if (!Files.exists(pathFolderUpload)) Files.createDirectory(pathFolderUpload);
        } catch (IOException e) {
            throw new PhotoException(EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.toString());
        }
    }

    @Override
    @Transactional
    public String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload) {
        String originalFileName = multipartFile.getOriginalFilename();
        String newPhotoName;
        getPhotoExtension(Objects.requireNonNull(originalFileName));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            String stringDate = sdf.format(new Date());
            newPhotoName = originalFileName.replaceAll(getPhotoExtension(originalFileName),
                    " - " + stringDate + getPhotoExtension(originalFileName));
            Files.copy(multipartFile.getInputStream(), pathFolderUpload.resolve(newPhotoName));
        } catch (IOException e) {
            throw new PhotoException(EExceptionMessage.THE_PHOTO_CANNOT_BE_SAVED + e.getMessage());
        }
        return newPhotoName;
    }

    public static String getPhotoExtension(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    @Override
    @Transactional
    public Photo savePhoto(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) {
        init(pathFolderUpload);
        try {
            Photo photo = new Photo();
            String newPhotoName = uploadFiles(multipartFile, pathFolderUpload);
            Photo entityForConvert = mapper.convertToEntity(photo, multipartFile, newPhotoName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (PhotoException exception) {
            throw new PhotoException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    @Override
    public PhotoResponse getPhotoResponse(Photo file) {
        return mapper.convertToResponse(file);
    }

    @Override
    @Transactional
    public List<Photo> savePhotos(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) {
        init(pathFolderUpload);
        try {
            List<Photo> photoList = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                Photo photo = new Photo();
                String newPhotoName = uploadFiles(multipartFile, pathFolderUpload);
                Photo entityForConvert = mapper.convertToEntity(photo, multipartFile, newPhotoName, pathFileUpload);
                Photo entityForSave = repository.save(entityForConvert);
                photoList.add((entityForSave));
            }
            return photoList;
        } catch (PhotoException exception) {
            throw new PhotoException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    @Override
    @Transactional
    public List<PhotoResponse> getPhotosResponses(List<Photo> photoList) {
        return mapper.convertToResponseList(photoList);
    }

    @Override
    @Transactional
    public Photo modifyPhoto(String id, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) {
        try {
            Photo entity = repository.searchById(id);
            deletePhotoByOriginalName(entity.getFileName(), pathFolderUpload);
            String newFileName = uploadFiles(multipartFile, pathFolderUpload);
            Photo entityForConvert = mapper.convertToEntity(entity, multipartFile, newFileName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (PhotoException exception) {
            throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
        } catch (Exception exception) {
            throw new PhotoException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    @Override
    @Transactional
    public void deletePhotoById(String id, Path pathFolderUpload) {
        Optional<Photo> answer = repository.findById(id);
        if (answer.isPresent()) {
            Photo entity = answer.get();
            repository.delete(entity);
            deletePhotoByOriginalName(entity.getFileName(), pathFolderUpload);
        } else {
            throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional
    public String deletePhotoByOriginalName(String originalName, Path pathFileUpload) {
        try {
            @SuppressWarnings("unused")
            Boolean delete = Files.deleteIfExists(pathFileUpload.resolve(originalName));
            return EExceptionMessage.PHOTO_DELETED.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return EExceptionMessage.ERROR_DELETING_PHOTO.toString();
        }
    }

    @Override
    @Transactional
    public void deleteAllPhotos(Path pathFolderUpload) {
        FileSystemUtils.deleteRecursively(pathFolderUpload.toFile());
    }

    @Override
    @Transactional(readOnly = true)
    public Photo getPhotoById(String id) {
        if (repository.existsById(id)) {
            return repository.searchById(id);
        } else {
            throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
        }
    }
}