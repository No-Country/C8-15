package com.nocountry.java_react.controller;

import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.exception.PhotographerException;
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

public interface IBuyerController {

    @PostMapping(path = "/save-buyer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> createBuyer(@Valid @RequestBody BuyerRequestCreate request) throws EmailAlreadyExistException, PhotographerException;

    @PutMapping(path = "/modify-buyer/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> modifyBuyer(@NotNull @PathVariable("id-buyer") String idBuyer,
                                              @Valid @RequestBody BuyerRequestModify request) throws EmailAlreadyExistException;

    @PostMapping(path = "/modify-password/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> modifyPassword(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                 @Valid @RequestBody BuyerRequestPassword request) throws BuyerException;

    @DeleteMapping(path = "/delete-buyer/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> deleteBuyer(@NotNull @PathVariable("id-buyer") String idBuyer);

    @GetMapping(path = "/get-by-id/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> getBuyerById(@NotNull @PathVariable("id-buyer") String idBuyer);

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BuyerResponse>> getAllBuyer();

    @PostMapping(path = "/add-photo/{id-buyer}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> addPhotoToBuyer(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                         String stringRequest,
                                                         @RequestParam(value = "photo") MultipartFile photo) throws BuyerException, PhotoException;

    @DeleteMapping(path = "/remove-photo/{id-buyer}/photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removePhotoToBuyer(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                            @NotNull @PathVariable("id-photo") String idPhoto) throws PhotoException, BuyerException;

    @DeleteMapping(path = "/remove-all-photos/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotographerResponse> removeAllPhotosToBuyer(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException, PhotoException;
}