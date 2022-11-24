package com.nocountry.java_react.controller.mp.impl;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.controller.mp.IPreferenceController;
import com.nocountry.java_react.dto.request.mp.PreferencePaymentRequest;
import com.nocountry.java_react.dto.response.mp.PaymentResponse;
import com.nocountry.java_react.service.mp.IPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.PREFERENCE_URI)
@RequiredArgsConstructor
public class PreferenceController implements IPreferenceController {

    private final IPreferenceService preferenceService;
    private static final String PUBLIC_KEY = "APP_USR-98a99e2d-1c4b-461f-8f88-a0fbd2008529";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPreference(@RequestBody PreferencePaymentRequest preferencePaymentRequest, Model model)
            throws MPApiException, MPException {
        model.addAttribute("publicKey", PUBLIC_KEY);
        PaymentResponse payment = preferenceService.getPaymentResponse(preferencePaymentRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }
}