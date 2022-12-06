package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.model.Photo;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@Service
public interface IPhotoService {

    @Transactional
    void initFolderUpload(Path pathFolderUpload) throws PhotoException;

    @Transactional
    String uploadPhotos(MultipartFile multipartFile, Path pathFolderUpload) throws PhotoException;

    @Transactional
    String uploadProfilePicture(MultipartFile multipartFile, Path pathFolderUpload) throws PhotoException;

    @Transactional
    Photo savePhoto(PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws PhotoException;

    @Transactional
    Photo saveProfilePicture(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws PhotoException;

    @Transactional
    PhotoResponse getPhotoResponse(Photo photo);

    @Transactional
    List<Photo> savePhotos(PhotoRequest photoRequest, List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws PhotoException;

    @Transactional
    List<PhotoResponse> getPhotosResponses(List<Photo> photoList);

    @Transactional
    Photo modifyPhoto(String idPhoto, PhotoRequest photoRequest, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws PhotoException;

    @Transactional
    void deletePhotoById(String idPhoto, Path pathFolderUpload) throws PhotoException;

    @Transactional
    String deletePhotoByOriginalName(String originalName, Path pathFileUpload) throws PhotoException;

    @Transactional
    void deleteAllPhotos(Path pathFolderUpload);

    @Transactional(readOnly = true)
    Photo getPhotoById(String idPhoto) throws PhotoException;

    Resource downloadPhoto(String idPhoto, Path pathFolderUpload) throws PhotoException, MalformedURLException;
}