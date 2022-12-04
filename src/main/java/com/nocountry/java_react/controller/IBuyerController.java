package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.util.List;

@Validated
public interface IBuyerController {

    @PostMapping(path = "/save-buyer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> createBuyer(@Valid @RequestBody BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException;

    @PutMapping(path = "/modify-buyer/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> modifyBuyer(@NotNull @PathVariable("id-buyer") String idBuyer, @Valid @RequestBody BuyerRequestModify request)
            throws EmailAlreadyExistException, BuyerException;

    @PostMapping(path = "/modify-password/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> modifyPassword(@NotNull @PathVariable("id-buyer") String idBuyer, @Valid @RequestBody BuyerRequestPassword request)
            throws BuyerException;

    @DeleteMapping(path = "/delete-buyer/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> deleteBuyer(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException;

    @GetMapping(path = "/get-by-id/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> getBuyerById(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException;

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BuyerResponse>> getAllBuyer() throws BuyerException;

    @PostMapping(path = "/buy-photo/{id-buyer}/photo/{id-photo}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> buyPhoto(@NotNull @PathVariable("id-buyer") String idBuyer, @NotNull @PathVariable("id-photo") String idPhoto, String stringRequest)
            throws PhotoException, BuyerException;

    @GetMapping(path = "/download-photo/{id-buyer}/photo/{id-photo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Resource> downloadPhoto(@NotNull @PathVariable("id-buyer") String idBuyer, @NotNull @PathVariable("id-photo") String idPhoto)
            throws MalformedURLException, PhotoException, BuyerException;

    @DeleteMapping(path = "/remove-photo/{id-buyer}/photo/{id-photo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> removePhotoToBuyer(@NotNull @PathVariable("id-buyer") String idBuyer, @NotNull @PathVariable("id-photo") String idPhoto)
            throws PhotoException, BuyerException;

    @DeleteMapping(path = "/remove-all-photos/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> removeAllPhotosToBuyer(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException, PhotoException;
}