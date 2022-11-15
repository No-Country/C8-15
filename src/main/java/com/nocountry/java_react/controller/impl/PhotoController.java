package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IPhotoController;
import com.nocountry.java_react.dto.request.PhotoRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.nocountry.java_react.commons.constants.Constants.PHOTO_URI;

@RestController
@RequestMapping(PHOTO_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController implements IPhotoController {

    private final IPhotoService service;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> create(@Valid @RequestBody PhotoRequest request) {
        PhotoResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/modify/{id-photo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> modify(@NotNull @PathVariable("id-photo") String idPhoto,
                                                @Valid @RequestBody PhotoRequest request) {
        PhotoResponse response = service.modify(idPhoto, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> delete(@NotNull @PathVariable("id-photo") String idPhoto) {
        service.delete(idPhoto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoResponse> getById(@NotNull @PathVariable("id-photo") String idPhoto) {
        PhotoResponse response = service.getById(idPhoto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhotoResponse>> getAll() {
        List<PhotoResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}