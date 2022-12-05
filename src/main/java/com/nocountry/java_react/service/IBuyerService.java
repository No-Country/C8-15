package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.buyer.BuyerRequestBuyPhoto;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public interface IBuyerService {

    @Transactional
    BuyerResponse saveBuyer(BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException;

    @Transactional
    BuyerResponse modifyBuyer(String idBuyer, BuyerRequestModify request) throws EmailAlreadyExistException, BuyerException;

    @Transactional
    BuyerResponse modifyPassword(String idBuyer, BuyerRequestPassword request) throws BuyerException;

    @Transactional
    void deleteBuyer(String idBuyer) throws BuyerException;

    @Transactional(readOnly = true)
    BuyerResponse getBuyerById(String idBuyer) throws BuyerException;

    @Transactional(readOnly = true)
    List<BuyerResponse> getAllBuyer() throws BuyerException;

    @Transactional
    void addPhotoToBuyer(String idBuyer, String stringRequest, MultipartFile photo) throws BuyerException, PhotoException;

    @Transactional
    void buyPhoto(String idBuyer, String idPhoto, BuyerRequestBuyPhoto request) throws BuyerException, PhotoException, IOException;

    @Transactional
    void removePhotoToBuyer(String idBuyer, String idPhoto) throws PhotoException, BuyerException;

    @Transactional
    void removeAllPhotosToBuyer(String idBuyer) throws BuyerException, PhotoException;

    @Transactional
    Resource downloadPhoto(String idBuyer, String idPhoto) throws PhotoException, BuyerException, MalformedURLException;
}