package com.nocountry.java_react.dto.request.mp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PreferencePaymentRequest {

    @JsonProperty("auto_return")
    private String autoReturn;

    // son necesarias en el back?
    @JsonProperty("back_url_success")
    private String backUrlSuccess;

    // son necesarias en el back?
    @JsonProperty("back_url_pending")
    private String backUrlPending;

    // son necesarias en el back?
    @JsonProperty("back_url_failure")
    private String backUrlFailure;

    // Constante, siempre es "ARS"
    @JsonProperty("currency_id")
    private String currencyId;

    private BuyerRequestCreate payer;

    //Constante en el front, siempre es 1
    private Integer installments;

    private OffsetDateTime dateRegistered;

    @JsonProperty("notification_url")
    private String notificationUrl;
}
