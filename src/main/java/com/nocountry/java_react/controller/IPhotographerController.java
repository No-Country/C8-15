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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IPhotographerController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> create(@Valid @RequestBody PhotographerRequest request);

    @PutMapping(path = "/modify/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> modify(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                @Valid @RequestBody PhotographerRequest request);

    @DeleteMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> delete(@NotNull @PathVariable("id-photographer") String idPhotographer);

    @GetMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> getById(@NotNull @PathVariable("id-photographer") String idPhotographer);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotographerResponse>> getAll();
}