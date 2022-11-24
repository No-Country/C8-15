package com.nocountry.java_react.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.exception.PhotographerException;
import com.nocountry.java_react.mapper.PhotographerMapper;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.model.Photographer;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.repository.IPhotographerRepository;
import com.nocountry.java_react.service.IPhotoService;
import com.nocountry.java_react.service.IPhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotographerServiceImpl implements IPhotographerService {

    private static final String REQUEST_WRONG_DATA = "request.wrong.data";
    private static final String PHOTOGRAPHER_NOT_FOUND = "photographer.not.found";
    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_PHOTOGRAPHER_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_PHOTOGRAPHER_IMAGE.toString();
    private final IPhotographerRepository repository;
    private final IPhotoRepository photoRepository;
    private final PhotographerMapper mapper;
    private final IPhotoService photoService;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public PhotographerResponse savePhotographer(PhotographerRequestCreate request) throws PhotographerException, EmailAlreadyExistException {
        try {
            Photographer entity = new Photographer();
            Photographer entityForConvert = mapper.convertToEntity(entity, request);
            Photographer entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } catch (PhotographerException exception) {
            throw new PhotographerException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public PhotographerResponse modifyPhotographer(String idPhotographer, PhotographerRequestModify request) throws EmailAlreadyExistException, PhotographerException {
        try {
            Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
            if (optionalPhotographer.isPresent()) {
                Photographer entity = optionalPhotographer.get();
                Photographer entityForConvert = mapper.convertToEntityModify(entity, request);
                Photographer entityForSave = repository.save(entityForConvert);
                return mapper.convertToResponse(entityForSave);
            } else {
                throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
            }
        } catch (PhotographerException exception) {
            throw new PhotographerException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
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
                throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
            }
        } catch (Exception exception) {
            throw new PhotographerException(messageSource.getMessage(REQUEST_WRONG_DATA, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional
    public void deletePhotographer(String idPhotographer) throws PhotographerException {
        Optional<Photographer> optionalPhotographer = repository.findById(idPhotographer);
        if (optionalPhotographer.isPresent()) {
            Photographer entity = optionalPhotographer.get();
            entity.setDeleted(!entity.isDeleted());
            entity.setUpdated(new Date());
            repository.save(entity);
        } else {
            throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PhotographerResponse getPhotographerById(String idPhotographer) throws PhotographerException {
        if (repository.existsById(idPhotographer)) {
            Photographer entity = repository.getReferenceById(idPhotographer);
            return mapper.convertToResponse(entity);
        } else {
            throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotographerResponse> getAllPhotographer() throws PhotographerException {
        List<Photographer> photographerList = repository.findAll();
        if (!photographerList.isEmpty()) {
            return mapper.convertToResponseList(photographerList);
        } else {
            throw new PhotographerException(messageSource.getMessage("the.list.of.photographers.is.empty", null, Locale.ENGLISH));
        }
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
            throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
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
                throw new PhotoException(messageSource.getMessage("photo.not.found", null, Locale.ENGLISH));
            }
        } else {
            throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
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
            throw new PhotographerException(messageSource.getMessage(PHOTOGRAPHER_NOT_FOUND, null, Locale.ENGLISH));
        }
    }
}