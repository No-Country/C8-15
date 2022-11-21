package com.nocountry.java_react.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.controller.IPhotoController;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.nocountry.java_react.commons.constants.Constants.PHOTO_URI;

@RestController
@RequestMapping(PHOTO_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController implements IPhotoController {

    private final IPhotoService service;
    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_PHOTO_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_PHOTO_IMAGE.toString();

    @Override
    public ResponseEntity<PhotoResponse> uploadPhoto(String stringRequest,
                                                     @RequestParam("photo") MultipartFile photo) {
        PhotoRequest photoRequest = new PhotoRequest();
        try {
            photoRequest = new ObjectMapper().readValue(stringRequest, PhotoRequest.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        PhotoResponse response = service.getPhotoResponse(service.savePhoto(photoRequest, photo, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotoResponse> modifyPhoto(@NotNull @PathVariable("id-photo") String idPhoto,
                                                     String stringRequest,
                                                     @RequestParam(value = "photo", required = false) MultipartFile photo) {
        PhotoRequest photoRequest = new PhotoRequest();
        try {
            photoRequest = new ObjectMapper().readValue(stringRequest, PhotoRequest.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        PhotoResponse response = service.getPhotoResponse(service.modifyPhoto(idPhoto, photoRequest, photo, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotoResponse> deletePhoto(@NotNull @PathVariable("id-photo") String idPhoto) {
        service.deletePhotoById(idPhoto, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PhotoResponse> deletePhotoByOriginalName(@PathVariable String originalName) {
        service.deletePhotoByOriginalName(originalName, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PhotoResponse> getPhotoById(@NotNull @PathVariable("id-photo") String idPhoto) {
        PhotoResponse response = service.getPhotoResponse(service.getPhotoById(idPhoto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id-photo") String idPhoto) throws Exception {
        Resource resource = service.downloadPhoto(idPhoto, pathFolderUpload);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}