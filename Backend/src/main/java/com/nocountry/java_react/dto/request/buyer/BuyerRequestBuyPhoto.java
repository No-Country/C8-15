package com.nocountry.java_react.dto.request.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BuyerRequestBuyPhoto {

    @NotBlank(message = "EL CAMPO QUE INDICA LA COMPRA DE LA FOTO NO PUEDE ESTAR VACÍO O SER NULO")
    @JsonProperty("purchasedPhoto")
    private String purchasedPhoto;
}