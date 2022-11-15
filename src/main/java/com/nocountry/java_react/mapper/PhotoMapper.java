package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EPhotoCategory;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.model.Photo;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoMapper {

    public Photo convertToEntity(Photo entity, PhotoRequest request) {
        if (request.getOriginalName() != null) entity.setOriginalName(request.getOriginalName());
        if (request.getFileName() != null) entity.setFileName(request.getFileName());
        if (request.getPath() != null) entity.setPath(request.getPath());
        EPhotoCategory category = EPhotoCategory.typeOrValue((request.getCategory().toUpperCase()));
        entity.setCategory(category);
        if (request.getAuthor() != null) entity.setAuthor(request.getAuthor());
        if (request.getLocation() != null) entity.setLocation(request.getLocation());
        return entity;
    }

    public PhotoResponse convertToResponse(Photo entity) {
        PhotoResponse response = new PhotoResponse();
        response.setIdPhoto(entity.getId());
        response.setOriginalName(entity.getOriginalName());
        response.setFileName(entity.getFileName());
        response.setPath(entity.getPath());
        response.setCategory(entity.getCategory().toString());
        response.setAuthor(entity.getAuthor());
        response.setLocation(entity.getLocation());
        // DATE TO STRING
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringRegistrationDate = sdf.format(entity.getRegistrationDate());
        String stringLastModification = sdf.format(entity.getModificationDate());
        response.setRegistrationDate(stringRegistrationDate);
        response.setModificationDate(stringLastModification);
        response.setSoftDelete(entity.getSoftDelete().toString());
        return response;
    }

    public List<PhotoResponse> convertToResponseList(List<Photo> photoList) {
        List<PhotoResponse> responseList = new ArrayList<>();
        for (Photo entity : photoList) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}