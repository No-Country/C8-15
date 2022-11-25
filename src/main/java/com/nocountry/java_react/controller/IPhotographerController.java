package com.nocountry.java_react.controller;

import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.exception.PhotographerException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface IPhotographerController {
    @PostMapping(path = "/save-photographer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> createPhotographer(@Valid @RequestBody PhotographerRequestCreate request) throws EmailAlreadyExistException, PhotographerException;

    @PutMapping(path = "/modify-photographer/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> modifyPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                            @Valid @RequestBody PhotographerRequestModify request) throws EmailAlreadyExistException, PhotographerException;

    @PostMapping(path = "/modify-password/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> modifyPassword(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                        @Valid @RequestBody PhotographerRequestPassword request) throws PhotographerException;

    @DeleteMapping(path = "/delete-photographer/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> deletePhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer) throws PhotographerException;

    @GetMapping(path = "/get-by-id/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> getPhotographerById(@NotNull @PathVariable("id-photographer") String idPhotographer) throws PhotographerException;

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotographerResponse>> getAllPhotographer() throws PhotographerException;

    @PostMapping(path = "/add-photo/{id-photographer}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> addPhotoToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                                String stringRequest,
                                                                @RequestParam(value = "photo") MultipartFile photo) throws PhotographerException, PhotoException;

    @DeleteMapping(path = "/remove-photo/{id-photographer}/photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removeFileToStudent(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                             @NotNull @PathVariable("id-photo") String idPhoto) throws PhotoException, PhotographerException;

    @DeleteMapping(path = "/remove-all-photos/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removeAllPhotosToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer) throws PhotographerException, PhotoException;
}