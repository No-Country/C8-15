package com.nocountry.java_react.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String typeException;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> moreInfo;

    public ErrorResponse(int statusCode, String typeException, String message, String moreInfo) {
        this.statusCode = statusCode;
        this.typeException = typeException;
        this.message = message;
        this.moreInfo = List.of(moreInfo);
    }
}