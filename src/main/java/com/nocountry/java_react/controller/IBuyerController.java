package com.nocountry.java_react.controller;

import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IBuyerController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> create(@Valid @RequestBody BuyerRequest request);

    @PutMapping(path = "/modify/{id-buyer}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> modify(@NotNull @PathVariable("id-buyer") String idBuyer,
                                         @Valid @RequestBody BuyerRequest request);

    @DeleteMapping(path = "/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> delete(@NotNull @PathVariable("id-buyer") String idBuyer);

    @GetMapping(path = "/{id-buyer}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BuyerResponse> getById(@NotNull @PathVariable("id-buyer") String idBuyer);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BuyerResponse>> getAll();
}