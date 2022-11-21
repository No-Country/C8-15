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
    PhotographerResponse save(PhotographerRequest request);

    @Transactional
    PhotographerResponse modify(String idPhotographer, PhotographerRequest request);

    @Transactional
    void delete(String idPhotographer);

    @Transactional(readOnly = true)
    PhotographerResponse getById(String idPhotographer);

    @Transactional(readOnly = true)
    List<PhotographerResponse> getAll();

    @Transactional
    void addPhotoToPhotographer(String idPhotographer, String photoRequest, MultipartFile photo);

    @Transactional
    void removePhotoToPhotographer(String idPhotographer, String idPhoto);

    @Transactional
    void removeAllPhotosToPhotographer(String idPhotographer);
}