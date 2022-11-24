package com.nocountry.java_react.service.mp.impl;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.nocountry.java_react.dto.request.mp.PreferencePaymentRequest;
import com.nocountry.java_react.dto.response.mp.PaymentResponse;
import com.nocountry.java_react.service.mp.IPreferenceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceServiceImpl implements IPreferenceService {

    private static final String BASE_DOMAIN = "https://f476-191-82-222-195.sa.ngrok.io";
    private static final String NOTIFICATION_URL = BASE_DOMAIN + "/api/notification/payment";
    private static final String PROD_ACCESS_TOKEN = "APP_USR-7741949629063748-112209-f875bd25dd28cfa07002150badab1770-1245290081";

    @Override
    public PaymentResponse getPaymentResponse(PreferencePaymentRequest preferencePaymentRequest) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(PROD_ACCESS_TOKEN);

        // Crea un objeto de preferencia
        PreferenceClient client = new PreferenceClient();

        // Crea un ítem en la preferencia
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("FOTO MONTAÑA")
                .quantity(1)
                .unitPrice(new BigDecimal("100"))
                .build();
        items.add(item);

        PreferencePaymentMethodsRequest preferencePaymentMethodsRequest = PreferencePaymentMethodsRequest
                .builder()
                .installments(1)  // --> DETERMINA LA CANTIDAD DE CUOTAS
                .build();

//        PreferenceRequest request = PreferenceRequest.builder().items(items).build();
//        client.create(request);

        PreferenceRequest preference = PreferenceRequest.builder()
                .items(items)
                .paymentMethods(preferencePaymentMethodsRequest)
                .notificationUrl(NOTIFICATION_URL)
                .build();

        Preference createdPayment = client.create(preference);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setSandboxInitPoint(createdPayment.getSandboxInitPoint());
        paymentResponse.setInitPoint(createdPayment.getInitPoint());
        System.out.println("INIT POINT: " + paymentResponse);
        return paymentResponse;
    }
}
