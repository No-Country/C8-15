package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IPhotoService {
    @Transactional
    PhotoResponse save(PhotoRequest request);

    @Transactional
    PhotoResponse modify(String idPhoto, PhotoRequest request);

    @Transactional
    void delete(String idPhoto);

    @Transactional(readOnly = true)
    PhotoResponse getById(String idPhoto);

    @Transactional(readOnly = true)
    List<PhotoResponse> getAll();
}