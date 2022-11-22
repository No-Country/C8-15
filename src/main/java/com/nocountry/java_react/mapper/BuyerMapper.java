package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EUserRole;
import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.model.Buyer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuyerMapper {

    private static final EUserRole role = EUserRole.BUYER;

    public Buyer convertToEntity(Buyer entity, BuyerRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getUserName() != null) entity.setUserName(request.getUserName());
        if (request.getPassword() != null) entity.setPassword(request.getPassword());
        entity.setRole(role);
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        return entity;
    }

    public BuyerResponse convertToResponse(Buyer entity) {
        BuyerResponse response = new BuyerResponse();
        response.setIdPhotographer(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setEmail(entity.getEmail());
        response.setUserName(entity.getUserName());
        response.setRole(entity.getRole().toString());
        response.setTelephone(entity.getTelephone());
        response.setCity(entity.getCity());
        response.setCountry(entity.getCountry());
        // DATE TO STRING
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringRegistrationDate = sdf.format(entity.getCreated());
        String stringLastModification = sdf.format(entity.getUpdated());
        response.setCreated(stringRegistrationDate);
        response.setUpdated(stringLastModification);
        response.setDeleted(String.valueOf(entity.isDeleted()));
        return response;
    }

    public List<BuyerResponse> convertToResponseList(List<Buyer> buyerList) {
        List<BuyerResponse> responseList = new ArrayList<>();
        for (Buyer entity : buyerList) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}