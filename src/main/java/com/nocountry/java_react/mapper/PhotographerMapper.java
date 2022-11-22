package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EUserRole;
import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.model.Photographer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotographerMapper {

    private static final EUserRole role = EUserRole.PHOTOGRAPHER;

    private final PhotoMapper photoMapper;

    public Photographer convertToEntity(Photographer entity, PhotographerRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getUserName() != null) entity.setUserName(request.getUserName());
        if (request.getPassword() != null) entity.setPassword(request.getPassword());
        entity.setRole(role);
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        if (request.getFacebookUrl() != null) entity.setFacebookUrl(request.getFacebookUrl());
        if (request.getInstagramUrl() != null) entity.setInstagramUrl(request.getInstagramUrl());
        return entity;
    }

    public PhotographerResponse convertToResponse(Photographer entity) {
        PhotographerResponse response = new PhotographerResponse();
        response.setIdPhotographer(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setEmail(entity.getEmail());
        response.setUserName(entity.getUserName());
        response.setRole(entity.getRole().toString());
        response.setTelephone(entity.getTelephone());
        response.setCity(entity.getCity());
        response.setCountry(entity.getCountry());
        response.setFacebookUrl(entity.getFacebookUrl());
        response.setInstagramUrl(entity.getInstagramUrl());

        // LIST OF PHOTOS
        List<PhotoResponse> photoList = new ArrayList<>(photoMapper.convertToResponseList(entity.getPhotos()));
        response.setPhotos(photoList);

        // DATE TO STRING
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreated = sdf.format(entity.getCreated());
        String stringUpdated = sdf.format(entity.getUpdated());
        response.setCreated(stringCreated);
        response.setUpdated(stringUpdated);
        response.setDeleted(String.valueOf(entity.isDeleted()));
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