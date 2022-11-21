package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IPhotographerService {

    @Transactional
    PhotographerResponse savePhotographer(PhotographerRequest request);

    @Transactional
    PhotographerResponse modifyPhotographer(String idPhotographer, PhotographerRequest request);

    @Transactional
    void deletePhotographer(String idPhotographer);

    @Transactional(readOnly = true)
    PhotographerResponse getPhotographerById(String idPhotographer);

    @Transactional(readOnly = true)
    List<PhotographerResponse> getAllPhotographer();

    @Transactional
    void addPhotoToPhotographer(String idPhotographer, String stringRequest, MultipartFile photo);

    @Transactional
    void removePhotoToPhotographer(String idPhotographer, String idPhoto);

    @Transactional
    void removeAllPhotosToPhotographer(String idPhotographer);
}