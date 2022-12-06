package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.controller.IBuyerController;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestBuyPhoto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping(Constants.BUYER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BuyerController implements IBuyerController {

    private final IBuyerService service;

    @Override
    public ResponseEntity<BuyerResponse> createBuyer(BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException {
        BuyerResponse response = service.saveBuyer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuyerResponse> modifyBuyer(String idBuyer, BuyerRequestModify request)
            throws EmailAlreadyExistException, BuyerException {
        BuyerResponse response = service.modifyBuyer(idBuyer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> modifyPassword(String idBuyer, BuyerRequestPassword request)
            throws BuyerException {
        service.modifyPassword(idBuyer, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> deleteBuyer(String idBuyer) throws BuyerException {
        service.deleteBuyer(idBuyer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BuyerResponse> getBuyerById(String idBuyer) throws BuyerException {
        BuyerResponse response = service.getBuyerById(idBuyer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BuyerResponse>> getAllBuyer() throws BuyerException {
        List<BuyerResponse> responseList = service.getAllBuyer();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> addProfilePictureToBuyer(String idBuyer, MultipartFile profilePicture) throws BuyerException, PhotoException {
        service.addProfilePictureToBuyer(idBuyer, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PhotographerResponse> removeProfilePictureToBuyer(String idBuyer) throws BuyerException, PhotoException {
        service.removeProfilePictureToBuyer(idBuyer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BuyerResponse> buyPhoto(String idBuyer, String idPhoto, BuyerRequestBuyPhoto request) throws PhotoException, BuyerException, IOException {
        service.buyPhoto(idBuyer, idPhoto, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Resource> downloadPhoto(String idBuyer, String idPhoto) throws MalformedURLException, PhotoException, BuyerException {
        Resource resource = service.downloadPhoto(idBuyer, idPhoto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<BuyerResponse> removePhotoToBuyer(String idBuyer, String idPhoto) throws PhotoException, BuyerException {
        service.removePhotoToBuyer(idBuyer, idPhoto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BuyerResponse> removeAllPhotosToBuyer(String idBuyer) throws BuyerException, PhotoException {
        service.removeAllPhotosToBuyer(idBuyer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}