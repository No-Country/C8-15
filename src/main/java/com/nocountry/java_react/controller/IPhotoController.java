package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.PhotoException;
import org.springframework.core.io.Resource;
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

public interface IPhotoController {
    @PostMapping(path = "/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> uploadPhoto(String stringRequest, @RequestParam("photo") MultipartFile photo) throws PhotoException;

    @PutMapping(path = "/modify-photo/{id-photo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<PhotoResponse> modifyPhoto(@NotNull @PathVariable("id-photo") String idPhoto,
                                              String stringRequest,
                                              @RequestParam(value = "photo", required = false) MultipartFile photo) throws PhotoException;

    @DeleteMapping(path = "/delete-photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> deletePhoto(@NotNull @PathVariable("id-photo") String idPhoto) throws PhotoException;

    @DeleteMapping(path = "/delete-photo-by-original-name/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> deletePhotoByOriginalName(@PathVariable String originalName);

    @GetMapping(path = "/get-photo-by-id/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> getPhotoById(@NotNull @PathVariable("id-photo") String idPhoto) throws PhotoException;

    @GetMapping("/download-photo/{id-photo}")
    ResponseEntity<Resource> downloadPhoto(@PathVariable("id-photo") String idPhoto) throws Exception;
}