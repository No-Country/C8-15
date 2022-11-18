package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.response.PhotoResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IPhotoController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> uploadPhoto(@RequestParam("file") MultipartFile photo);

    @PostMapping(path = "/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotoResponse>> uploadPhotos(@RequestParam("files") List<MultipartFile> photos);

    @PutMapping(path = "/modify/{id-photo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<PhotoResponse> modifyPhoto(@NotNull @PathVariable("id-photo") String idPhoto,
                                              @RequestParam("file") MultipartFile photo);

    @DeleteMapping(path = "/delete/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> deletePhoto(@NotNull @PathVariable("id-photo") String idPhoto);

    @DeleteMapping(path = "/delete-by-original-name/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> deletePhotoByOriginalName(@PathVariable String originalName);

    @GetMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> getPhotoById(@NotNull @PathVariable("id-photo") String idPhoto);
}