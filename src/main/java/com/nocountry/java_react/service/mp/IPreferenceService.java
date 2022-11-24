package com.nocountry.java_react.service.mp;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.nocountry.java_react.dto.request.mp.PreferencePaymentRequest;
import com.nocountry.java_react.dto.response.mp.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface IPreferenceService {
    PaymentResponse getPaymentResponse(PreferencePaymentRequest preferencePaymentRequest) throws MPException, MPApiException;
}
