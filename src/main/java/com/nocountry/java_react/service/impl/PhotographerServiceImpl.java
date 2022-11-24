package com.nocountry.java_react.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotographerException;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.mapper.PhotographerMapper;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.model.Photographer;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.repository.IPhotographerRepository;
import com.nocountry.java_react.service.IPhotoService;
import com.nocountry.java_react.service.IPhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotographerServiceImpl implements IPhotographerService {

    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_PHOTOGRAPHER_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_PHOTOGRAPHER_IMAGE.toString();
    private final IPhotographerRepository repository;
    private final IPhotoRepository photoRepository;
    private final PhotographerMapper mapper;
    private final IPhotoService photoService;

    @Override
    @Transactional
    public PhotographerResponse savePhotographer(PhotographerRequestCreate request) throws PhotographerException, EmailAlreadyExistException {
        Photographer entity = new Photographer();
        Photographer entityForConvert = mapper.convertToEntity(entity, request);
        Photographer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public PhotographerResponse modifyPhotographer(String idPhotographer, PhotographerRequestModify request) throws EmailAlreadyExistException {
        Photographer entity = repository.getReferenceById(idPhotographer);
        Photographer entityForConvert = mapper.convertToEntityModify(entity, request);
        Photographer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public PhotographerResponse modifyPassword(String idPhotographer, PhotographerRequestPassword request) throws PhotographerException {
        try {
            Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
            if (optionalPhotographer.isPresent()) {
                Photographer photographer = optionalPhotographer.get();
                Photographer entityForConvert = mapper.convertToEntityModifyPassword(photographer, request);
                Photographer entityForSave = repository.save(entityForConvert);
                return mapper.convertToResponse(entityForSave);
            } else {
                throw new PhotographerException(EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString());
            }
        } catch (Exception exception) {
            throw new PhotographerException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    @Override
    @Transactional
    public void deletePhotographer(String idPhotographer) {
        Optional<Photographer> answer = repository.findById(idPhotographer);
        if (answer.isPresent()) {
            Photographer entity = answer.get();
            entity.setDeleted(!entity.isDeleted());
            entity.setUpdated(new Date());
            repository.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PhotographerResponse getPhotographerById(String idPhotographer) {
        if (repository.existsById(idPhotographer)) {
            Photographer entity = repository.getReferenceById(idPhotographer);
            return mapper.convertToResponse(entity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotographerResponse> getAllPhotographer() {
        List<Photographer> photographerList = repository.findAll();
        return mapper.convertToResponseList(photographerList);
    }

    @Override
    @Transactional
    public void addPhotoToPhotographer(String idPhotographer, String stringRequest, MultipartFile photo) throws PhotographerException, PhotoException {
        Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
        if (optionalPhotographer.isPresent()) {
            Photographer photographer = repository.getReferenceById(idPhotographer);
            // ADD PHOTOS TO PHOTOGRAPHER
            addPhotoToPhotographer(photographer, stringRequest, photo);
            repository.save(photographer);
        } else {
            throw new PhotographerException(EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString());
        }
    }

    private void addPhotoToPhotographer(Photographer photographer, String stringRequest, MultipartFile photo) throws PhotoException {
        PhotoRequest photoRequest = new PhotoRequest();
        try {
            photoRequest = new ObjectMapper().readValue(stringRequest, PhotoRequest.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Photo savePhoto = photoService.savePhoto(photoRequest, photo, pathFolderUpload, pathFileUpload);

        savePhoto.setPhotographer(photographer);
        photographer.getPhotos().add(savePhoto);
    }

    @Override
    @Transactional
    public void removePhotoToPhotographer(String idPhotographer, String idPhoto) throws PhotographerException, PhotoException {
        Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
        if (optionalPhotographer.isPresent()) {
            Photographer photographer = repository.getReferenceById(idPhotographer);
            Optional<Photo> optionalPhoto = photoRepository.findById(idPhoto);
            if (optionalPhoto.isPresent()) {
                Photo photo = photoRepository.getReferenceById(idPhoto);
                List<Photo> photoList = photographer.getPhotos();
                photoList.remove(photo);
                photographer.setPhotos(photoList);
                photoService.deletePhotoById(idPhoto, pathFolderUpload);
                repository.save(photographer);
            } else {
                throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
            }
        } else {
            throw new PhotographerException(EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional
    public void removeAllPhotosToPhotographer(String idPhotographer) throws PhotographerException, PhotoException {
        Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
        if (optionalPhotographer.isPresent()) {
            Photographer photographer = repository.getReferenceById(idPhotographer);
            List<Photo> photoList = photographer.getPhotos();
            for (Photo photo : photoList) {
                photoService.deletePhotoById(photo.getId(), pathFolderUpload);
            }
            photoList.clear();
            photographer.setPhotos(photoList);
            repository.save(photographer);
        } else {
            throw new PhotographerException(EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString());
        }
    }
}