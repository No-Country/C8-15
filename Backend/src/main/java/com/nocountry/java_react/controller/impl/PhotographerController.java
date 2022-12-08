package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IPhotographerController;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.exception.PhotographerException;
import com.nocountry.java_react.service.IPhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.nocountry.java_react.commons.constants.Constants.PHOTOGRAPHER_URI;

@RestController
@RequestMapping(PHOTOGRAPHER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotographerController implements IPhotographerController {

    private final IPhotographerService service;

    @Override
    public ResponseEntity<PhotographerResponse> createPhotographer(PhotographerRequestCreate request) throws EmailAlreadyExistException, PhotographerException {
        PhotographerResponse response = service.savePhotographer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PhotographerResponse> modifyPhotographer(String idPhotographer, PhotographerRequestModify request)
            throws EmailAlreadyExistException, PhotographerException {
        PhotographerResponse response = service.modifyPhotographer(idPhotographer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotographerResponse> modifyPassword(String idPhotographer, PhotographerRequestPassword request)
            throws PhotographerException {
        service.modifyPassword(idPhotographer, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotographerResponse> deletePhotographer(String idPhotographer) throws PhotographerException {
        service.deletePhotographer(idPhotographer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PhotographerResponse> getPhotographerById(String idPhotographer) throws PhotographerException {
        PhotographerResponse response = service.getPhotographerById(idPhotographer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PhotographerResponse>> getAllPhotographer() throws PhotographerException {
        List<PhotographerResponse> responseList = service.getAllPhotographer();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotographerResponse> addProfilePictureToPhotographer(String idPhotographer, MultipartFile profilePicture)
            throws PhotographerException, PhotoException {
        service.addProfilePictureToPhotographer(idPhotographer, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removeProfilePictureToPhotographer(String idPhotographer) throws PhotographerException, PhotoException {
        service.removeProfilePictureToPhotographer(idPhotographer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> addPhotoToPhotographer(String idPhotographer, String stringRequest, MultipartFile photo)
            throws PhotographerException, PhotoException {
        service.addPhotoToPhotographer(idPhotographer, stringRequest, photo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removePhotoToPhotographer(String idPhotographer, String idPhoto)
            throws PhotoException, PhotographerException {
        service.removePhotoToPhotographer(idPhotographer, idPhoto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removeAllPhotosToPhotographer(String idPhotographer) throws PhotographerException, PhotoException {
        service.removeAllPhotosToPhotographer(idPhotographer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}