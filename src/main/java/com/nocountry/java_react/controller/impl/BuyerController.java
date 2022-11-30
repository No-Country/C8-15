package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.controller.IBuyerController;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.service.IBuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(Constants.BUYER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BuyerController implements IBuyerController {

    private final IBuyerService service;

    @Override
    public ResponseEntity<BuyerResponse> createBuyer(@Valid @RequestBody BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException {
        BuyerResponse response = service.saveBuyer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuyerResponse> modifyBuyer(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                     @Valid @RequestBody BuyerRequestModify request) throws EmailAlreadyExistException, BuyerException {
        BuyerResponse response = service.modifyBuyer(idBuyer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> modifyPassword(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                        @Valid @RequestBody BuyerRequestPassword request) throws BuyerException {
        service.modifyPassword(idBuyer, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> deleteBuyer(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException {
        service.deleteBuyer(idBuyer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BuyerResponse> getBuyerById(@NotNull @PathVariable("id-buyer") String idBuyer) throws BuyerException {
        BuyerResponse response = service.getBuyerById(idBuyer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BuyerResponse>> getAllBuyer() throws BuyerException {
        List<BuyerResponse> responseList = service.getAllBuyer();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotographerResponse> addPhotoToBuyer(String idBuyer, String stringRequest, MultipartFile photo) throws BuyerException, PhotoException {
        service.addPhotoToBuyer(idBuyer, stringRequest, photo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removePhotoToBuyer(String idBuyer, String idPhoto) throws PhotoException, BuyerException {
        service.removePhotoToBuyer(idBuyer, idPhoto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removeAllPhotosToBuyer(String idBuyer) throws BuyerException, PhotoException {
        service.removeAllPhotosToBuyer(idBuyer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Resource> downloadPhoto(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                  @NotNull @PathVariable("id-photo") String idPhoto) throws Exception {
        Resource resource = service.downloadPhoto(idBuyer, idPhoto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}