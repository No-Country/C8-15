package com.nocountry.java_react.service.impl;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.mapper.PhotoMapper;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements IPhotoService {

    private static final String REQUEST_WRONG_DATA = "request.wrong.data";
    private static final String PHOTO_NOT_FOUND = "photo.not.found";
    private final IPhotoRepository repository;
    private final PhotoMapper mapper;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public void initFolderUpload(Path pathFolderUpload) throws PhotoException {
        try {
            if (!Files.exists(pathFolderUpload)) Files.createDirectory(pathFolderUpload);
        } catch (IOException e) {
            throw new PhotoException(messageSource.getMessage("the.folder.cannot.be.initialized", null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public String uploadPhotos(MultipartFile multipartFile, Path pathFolderUpload) throws PhotoException {
        String originalFileName = multipartFile.getOriginalFilename();
        String newPhotoName;
        getPhotoExtension(Objects.requireNonNull(originalFileName));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            String stringDate = sdf.format(new Date());
            newPhotoName = UUID.randomUUID() + " - " + stringDate + " - " + originalFileName.
                    replaceAll(getPhotoExtension(originalFileName), getPhotoExtension(originalFileName));
            Files.copy(multipartFile.getInputStream(), pathFolderUpload.resolve(newPhotoName));
        } catch (IOException e) {
            throw new PhotoException(messageSource.getMessage("the.photo.cannot.be.saved", null, Locale.ENGLISH));
        }
        return newPhotoName;
    }

    public static String getPhotoExtension(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    @Override
    @Transactional
    public Photo savePhoto(PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws PhotoException {
        initFolderUpload(pathFolderUpload);
        try {
            Photo photo = new Photo();
            String newPhotoName = uploadPhotos(multipartFile, pathFolderUpload);
            Photo entityForConvert = mapper.convertToEntity(photoRequest, photo, multipartFile, newPhotoName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (PhotoException exception) {
            throw new PhotoException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
    }

    @Override
    public PhotoResponse getPhotoResponse(Photo photo) {
        return mapper.convertToResponse(photo);
    }

    @Override
    @Transactional
    public List<Photo> savePhotos(PhotoRequest photoRequest, List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws PhotoException {
        initFolderUpload(pathFolderUpload);
        try {
            List<Photo> photoList = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                Photo photo = new Photo();
                String newPhotoName = uploadPhotos(multipartFile, pathFolderUpload);
                Photo entityForConvert = mapper.convertToEntity(photoRequest, photo, multipartFile, newPhotoName, pathFileUpload);
                Photo entityForSave = repository.save(entityForConvert);
                photoList.add((entityForSave));
            }
            return photoList;
        } catch (PhotoException exception) {
            throw new PhotoException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public List<PhotoResponse> getPhotosResponses(List<Photo> photoList) {
        return mapper.convertToResponseList(photoList);
    }

    @Override
    @Transactional
    public Photo modifyPhoto(String idPhoto, PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws PhotoException {
        try {
            Photo photo = repository.getReferenceById(idPhoto);
            if (multipartFile.isEmpty()) {
                Photo entityForConvert = mapper.convertToEntityModify(photoRequest, photo);
                return repository.save(entityForConvert);
            } else {
                deletePhotoByOriginalName(photo.getFileName(), pathFolderUpload);
                String newFileName = uploadPhotos(multipartFile, pathFolderUpload);
                Photo entityForConvert = mapper.convertToEntity(photoRequest, photo, multipartFile, newFileName, pathFileUpload);
                return repository.save(entityForConvert);
            }
        } catch (PhotoException exception) {
            throw new PhotoException(messageSource.getMessage(PHOTO_NOT_FOUND, null, Locale.ENGLISH));
        } catch (Exception exception) {
            throw new PhotoException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public void deletePhotoById(String idPhoto, Path pathFolderUpload) throws PhotoException {
        Optional<Photo> optionalPhoto = repository.findById(idPhoto);
        if (optionalPhoto.isPresent()) {
            Photo entity = optionalPhoto.get();
            repository.delete(entity);
            deletePhotoByOriginalName(entity.getFileName(), pathFolderUpload);
        } else {
            throw new PhotoException(messageSource.getMessage(PHOTO_NOT_FOUND, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public String deletePhotoByOriginalName(String originalName, Path pathFileUpload) {
        try {
            @SuppressWarnings("unused")
            Boolean delete = Files.deleteIfExists(pathFileUpload.resolve(originalName));
            return messageSource.getMessage("photo.deleted", null, Locale.ENGLISH);
        } catch (IOException e) {
            e.printStackTrace();
            return messageSource.getMessage("error.deleting.photo", null, Locale.ENGLISH);
        }
    }

    @Override
    @Transactional
    public void deleteAllPhotos(Path pathFolderUpload) {
        FileSystemUtils.deleteRecursively(pathFolderUpload.toFile());
    }

    @Override
    @Transactional(readOnly = true)
    public Photo getPhotoById(String idPhoto) throws PhotoException {
        if (repository.existsById(idPhoto)) {
            return repository.getReferenceById(idPhoto);
        } else {
            throw new PhotoException(messageSource.getMessage(PHOTO_NOT_FOUND, null, Locale.ENGLISH));
        }
    }

    @Override
    public Resource downloadPhoto(String idPhoto, Path pathFolderUpload) throws Exception {
        Optional<Photo> optionalPhoto = repository.findById(idPhoto);
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            String name = photo.getFileName();
            Path file = pathFolderUpload.resolve(name);
            return new UrlResource(file.toUri());
        } else {
            throw new PhotoException(messageSource.getMessage(PHOTO_NOT_FOUND, null, Locale.ENGLISH));
        }
    }
}