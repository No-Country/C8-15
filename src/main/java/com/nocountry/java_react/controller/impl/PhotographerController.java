package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IPhotographerController;
import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.service.IPhotographerService;
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

import static com.nocountry.java_react.commons.constants.Constants.PHOTOGRAPHER_URI;

@RestController
@RequestMapping(PHOTOGRAPHER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotographerController implements IPhotographerController {

    private final IPhotographerService service;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> create(@Valid @RequestBody PhotographerRequest request) {
        PhotographerResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/modify/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> modify(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                       @Valid @RequestBody PhotographerRequest request) {
        PhotographerResponse response = service.modify(idPhotographer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> delete(@NotNull @PathVariable("id-photographer") String idPhotographer) {
        service.delete(idPhotographer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> getById(@NotNull @PathVariable("id-photographer") String idPhotographer) {
        PhotographerResponse response = service.getById(idPhotographer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhotographerResponse>> getAll() {
        List<PhotographerResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}