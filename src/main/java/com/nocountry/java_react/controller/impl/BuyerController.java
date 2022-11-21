package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.controller.IBuyerController;
import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.service.IBuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<BuyerResponse> create(@Valid @RequestBody BuyerRequest request) {
        BuyerResponse response = service.saveBuyer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuyerResponse> modify(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                @Valid @RequestBody BuyerRequest request) {
        BuyerResponse response = service.modifyBuyer(idBuyer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyerResponse> delete(@NotNull @PathVariable("id-buyer") String idBuyer) {
        service.deleteBuyer(idBuyer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BuyerResponse> getById(@NotNull @PathVariable("id-buyer") String idBuyer) {
        BuyerResponse response = service.getBuyerById(idBuyer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BuyerResponse>> getAll() {
        List<BuyerResponse> responseList = service.getAllBuyer();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}