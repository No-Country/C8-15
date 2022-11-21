package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

public interface IPhotographerController {
    @PostMapping(path = "/save-photographer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> create(@Valid @RequestBody PhotographerRequest request);

    @PutMapping(path = "/modify-photographer/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> modify(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                @Valid @RequestBody PhotographerRequest request);

    @DeleteMapping(path = "/delete-photographer/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> delete(@NotNull @PathVariable("id-photographer") String idPhotographer);

    @GetMapping(path = "/get-by-id/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> getById(@NotNull @PathVariable("id-photographer") String idPhotographer);

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotographerResponse>> getAll();

    @PostMapping(path = "/add-photo/{id-photographer}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> addPhotoToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                                       String stringRequest,
                                                                       @RequestParam(value = "photo") MultipartFile photo);

    @DeleteMapping(path = "/remove-photo/{id-photographer}/photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removeFileToStudent(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                             @NotNull @PathVariable("id-photo") String idPhoto);

    @DeleteMapping(path = "/remove-all-photos/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removeAllPhotosToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer);
}