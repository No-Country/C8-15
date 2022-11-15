package com.nocountry.java_react.mapper;

import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.model.Photographer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhotographerMapper {
    public Photographer convertToEntity(Photographer entity, PhotographerRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getDocument() != null) entity.setDocument(request.getDocument());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getUserName() != null) entity.setUserName(request.getUserName());
        if (request.getPassword() != null) entity.setPassword(request.getPassword());
        if (request.getRole() != null) entity.setRole(request.getRole());
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        if (request.getFacebookUrl() != null) entity.setFacebookUrl(request.getFacebookUrl());
        if (request.getInstagramUrl() != null) entity.setInstagramUrl(entity.getInstagramUrl());
        return entity;
    }

    public PhotographerResponse convertToResponse(Photographer entity) {
        PhotographerResponse response = new PhotographerResponse();
        response.setIdPhotographer(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setDocument(entity.getDocument());
        response.setEmail(entity.getEmail());
        response.setUserName(entity.getUserName());
        response.setRole(entity.getRole());
        response.setTelephone(entity.getTelephone());
        response.setCountry(entity.getCity());
        response.setCountry(entity.getCountry());
        response.setFacebookUrl(entity.getFacebookUrl());
        response.setInstagramUrl(entity.getInstagramUrl());
        // DATE TO STRING
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringRegistrationDate = sdf.format(entity.getRegistrationDate());
        String stringLastModification = sdf.format(entity.getModificationDate());
        response.setRegistrationDate(stringRegistrationDate);
        response.setModificationDate(stringLastModification);
        response.setSoftDelete(entity.getSoftDelete().toString());
        return response;
    }

    public List<PhotographerResponse> convertToResponseList(List<Photographer> photographerList) {
        List<PhotographerResponse> responseList = new ArrayList<>();
        for (Photographer entity : photographerList) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}