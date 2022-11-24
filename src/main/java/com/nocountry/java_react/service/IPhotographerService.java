package com.nocountry.java_react.service;

import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.exception.PhotographerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IPhotographerService {

    @Transactional
    PhotographerResponse savePhotographer(PhotographerRequestCreate request) throws EmailAlreadyExistException, PhotographerException;

    @Transactional
    PhotographerResponse modifyPhotographer(String idPhotographer, PhotographerRequestModify request) throws EmailAlreadyExistException, PhotographerException;

    @Transactional
    PhotographerResponse modifyPassword(String idPhotographer, PhotographerRequestPassword request) throws PhotographerException;

    @Transactional
    void deletePhotographer(String idPhotographer) throws PhotographerException;

    @Transactional(readOnly = true)
    PhotographerResponse getPhotographerById(String idPhotographer) throws PhotographerException;

    @Transactional(readOnly = true)
    List<PhotographerResponse> getAllPhotographer() throws PhotographerException;

    @Transactional
    void addPhotoToPhotographer(String idPhotographer, String stringRequest, MultipartFile photo) throws PhotographerException, PhotoException;

    @Transactional
    void removePhotoToPhotographer(String idPhotographer, String idPhoto) throws PhotographerException, PhotoException;

    @Transactional
    void removeAllPhotosToPhotographer(String idPhotographer) throws PhotographerException, PhotoException;
}