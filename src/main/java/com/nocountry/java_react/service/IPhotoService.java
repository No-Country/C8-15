package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.model.Photo;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
public interface IPhotoService {

    @Transactional
    void init(Path pathFolderUpload);

    @Transactional
    String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload);

    @Transactional
    Photo savePhoto(PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload);

    PhotoResponse getPhotoResponse(Photo file);

    @Transactional
    List<Photo> savePhotos(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload);

    @Transactional
    List<PhotoResponse> getPhotosResponses(List<Photo> photoList);

    @Transactional
    Photo modifyPhoto(String id, PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload);

    @Transactional
    void deletePhotoById(String id, Path pathFolderUpload);

    @Transactional
    String deletePhotoByOriginalName(String originalName, Path pathFileUpload);

    @Transactional
    void deleteAllPhotos(Path pathFolderUpload);

    @Transactional(readOnly = true)
    Photo getPhotoById(String id);

    Resource downloadPhoto(String idPhoto) throws Exception;
}