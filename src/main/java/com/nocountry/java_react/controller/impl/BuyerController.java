package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.controller.IBuyerController;
import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.service.IBuyerService;
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

@RestController
@RequestMapping(Constants.BUYER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BuyerController implements IBuyerController {

    private final IBuyerService service;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyerResponse> create(@Valid @RequestBody BuyerRequest request) {
        BuyerResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/modify/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyerResponse> modify(@NotNull @PathVariable("id-buyer") String idBuyer,
                                                @Valid @RequestBody BuyerRequest request) {
        BuyerResponse response = service.modify(idBuyer, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyerResponse> delete(@NotNull @PathVariable("id-buyer") String idBuyer) {
        service.delete(idBuyer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping(path = "/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyerResponse> getById(@NotNull @PathVariable("id-buyer") String idBuyer) {
        BuyerResponse response = service.getById(idBuyer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuyerResponse>> getAll() {
        List<BuyerResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}