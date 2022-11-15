package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IPhotoController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> create(@Valid @RequestBody PhotoRequest request);

    @PutMapping(path = "/modify/{id-photo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> modify(@NotNull @PathVariable("id-photo") String idPhoto,
                                         @Valid @RequestBody PhotoRequest request);

    @DeleteMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> delete(@NotNull @PathVariable("id-photo") String idPhoto);

    @GetMapping(path = "/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotoResponse> getById(@NotNull @PathVariable("id-photo") String idPhoto);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotoResponse>> getAll();
}