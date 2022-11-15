package com.nocountry.java_react.service;

import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IBuyerService {

    @Transactional
    BuyerResponse save(BuyerRequest request);

    @Transactional
    BuyerResponse modify(String idBuyer, BuyerRequest request);

    @Transactional
    void delete(String idBuyer);

    @Transactional(readOnly = true)
    BuyerResponse getById(String idBuyer);

    @Transactional(readOnly = true)
    List<BuyerResponse> getAll();
}