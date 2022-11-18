package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EPhotoCategory;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.model.Photo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoMapper {

    public Photo convertToEntity(PhotoRequest photoRequest, Photo entity, MultipartFile file, String newFileName, String pathFileUpload) {
        extractedForConvertToEntity(photoRequest, entity, file, newFileName, pathFileUpload);
        return entity;
    }

    private static void extractedForConvertToEntity(PhotoRequest photoRequest, Photo entity, MultipartFile file, String newFileName, String pathFileUpload) {
        String path = pathFileUpload + newFileName;
        entity.setOriginalName(file.getOriginalFilename());
        entity.setFileName(newFileName);
        entity.setPath(path);
        EPhotoCategory category = EPhotoCategory.valueOf(photoRequest.getCategory().toUpperCase());
        entity.setCategory(category);
        entity.setAuthor(photoRequest.getAuthor());
        entity.setLocation(photoRequest.getLocation());
        entity.setDescription(photoRequest.getDescription());
    }

    public Photo convertToEntityModify(PhotoRequest photoRequest, Photo entity) {
        extractedForConvertToEntityModify(photoRequest, entity);
        return entity;
    }

    private static void extractedForConvertToEntityModify(PhotoRequest photoRequest, Photo entity) {
            EPhotoCategory category = EPhotoCategory.valueOf(photoRequest.getCategory().toUpperCase());
            entity.setCategory(category);
            entity.setAuthor(photoRequest.getAuthor());
            entity.setLocation(photoRequest.getLocation());
            entity.setDescription(photoRequest.getDescription());
    }

    public PhotoResponse convertToResponse(Photo entity) {
        PhotoResponse response = new PhotoResponse();
        extractedForConvertToResponse(entity, response);
        return response;
    }

    private static void extractedForConvertToResponse(Photo entity, PhotoResponse response) {
        response.setIdPhoto(entity.getId());
        response.setOriginalName(entity.getOriginalName());
        response.setFileName(entity.getFileName());
        response.setPath(entity.getPath());
        response.setCategory(entity.getCategory().toString());
        response.setAuthor(entity.getAuthor());
        response.setLocation(entity.getLocation());
        response.setDescription(entity.getDescription());
        // DATE TO STRING
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreated = sdf.format(entity.getCreated());
        String stringUpdated = sdf.format(entity.getUpdated());
        response.setCreated(stringCreated);
        response.setUpdated(stringUpdated);
        response.setDeleted(entity.getDeleted().toString());
    }

    public List<PhotoResponse> convertToResponseList(List<Photo> list) {
        List<PhotoResponse> responseList = new ArrayList<>();
        for (Photo entity : list) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}