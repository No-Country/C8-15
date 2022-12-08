package com.nocountry.java_react.controller.mp;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.nocountry.java_react.dto.request.mp.PreferencePaymentRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPreferenceController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createPreference(@RequestBody PreferencePaymentRequest preferencePaymentRequest, Model model)
            throws MPApiException, MPException;
}
