package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IBuyerService {

    @Transactional
    BuyerResponse saveBuyer(BuyerRequest request);

    @Transactional
    BuyerResponse modifyBuyer(String idBuyer, BuyerRequest request);

    @Transactional
    void deleteBuyer(String idBuyer);

    @Transactional(readOnly = true)
    BuyerResponse getBuyerById(String idBuyer);

    @Transactional(readOnly = true)
    List<BuyerResponse> getAllBuyer();
}