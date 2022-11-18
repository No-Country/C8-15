package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.controller.IPhotoController;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> uploadPhoto(@RequestParam("file") MultipartFile photo) {
        PhotoResponse response = service.getPhotoResponse(service.savePhoto(photo, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhotoResponse>> uploadPhotos(@RequestParam("files") List<MultipartFile> photos) {
        List<PhotoResponse> responseList = service.getPhotosResponses(service.savePhotos(photos, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/modify/{id-photo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoResponse> modifyPhoto(@NotNull @PathVariable("id-photo") String idPhoto,
                                                     @RequestParam("file") MultipartFile photo) {
        PhotoResponse response = service.getPhotoResponse(service.modifyPhoto(idPhoto, photo, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/delete/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> deletePhoto(@NotNull @PathVariable("id-photo") String idPhoto) {
        service.deletePhotoById(idPhoto, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping(path = "/delete-by-original-name/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> deletePhotoByOriginalName(@PathVariable String originalName) {
        service.deletePhotoByOriginalName(originalName, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> getPhotoById(@NotNull @PathVariable("id-photo") String idPhoto) {
        PhotoResponse response = service.getPhotoResponse(service.getPhotoById(idPhoto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}