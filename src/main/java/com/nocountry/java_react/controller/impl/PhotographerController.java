package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IPhotographerController;
import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.service.IPhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> create(@Valid @RequestBody PhotographerRequest request) {
        PhotographerResponse response = service.savePhotographer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    //@PutMapping(path = "/modify/{id-photographer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> modify(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                       @Valid @RequestBody PhotographerRequest request) {
        PhotographerResponse response = service.modifyPhotographer(idPhotographer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    //@DeleteMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> delete(@NotNull @PathVariable("id-photographer") String idPhotographer) {
        service.deletePhotographer(idPhotographer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    //@GetMapping(path = "/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> getById(@NotNull @PathVariable("id-photographer") String idPhotographer) {
        PhotographerResponse response = service.getPhotographerById(idPhotographer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhotographerResponse>> getAll() {
        List<PhotographerResponse> responseList = service.getAllPhotographer();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
//    @PostMapping(path = "/add-photo/{id-photographer}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
//    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> addPhotoToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                                       String stringRequest,
                                                                       @RequestParam(value = "photo") MultipartFile photo) {
        service.addPhotoToPhotographer(idPhotographer, stringRequest, photo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    //@DeleteMapping(path = "/remove-photo/{id-photographer}/photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> removeFileToStudent(@NotNull @PathVariable("id-photographer") String idPhotographer,
                                                                    @NotNull @PathVariable("id-photo") String idPhoto) {
        service.removePhotoToPhotographer(idPhotographer, idPhoto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    //@DeleteMapping(path = "/remove-all-photos/{id-photographer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotographerResponse> removeAllPhotosToPhotographer(@NotNull @PathVariable("id-photographer") String idPhotographer) {
        service.removeAllPhotosToPhotographer(idPhotographer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}