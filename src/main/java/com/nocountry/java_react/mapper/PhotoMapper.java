package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.model.Photo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoMapper {

    public Photo convertToEntity(PhotoRequest photoRequest, Photo entity, MultipartFile file, String newFileName, String pathFileUpload) throws PhotoException {
        extractedForConvertToEntity(photoRequest, entity, file, newFileName, pathFileUpload);
        return entity;
    }

    private static void extractedForConvertToEntity(PhotoRequest photoRequest, Photo entity, MultipartFile file, String newFileName, String pathFileUpload) throws PhotoException {
        String path = pathFileUpload + newFileName;
        entity.setOriginalName(file.getOriginalFilename());
        entity.setFileName(newFileName);
        entity.setPath(path);
        validateRequest(photoRequest);
        entity.setCategory(photoRequest.getCategory());
        entity.setAuthor(photoRequest.getAuthor());
        entity.setLocation(photoRequest.getLocation());
        entity.setDescription(photoRequest.getDescription());
        double stringToDouble = Double.parseDouble(photoRequest.getPrice().replace(",", "."));
        BigDecimal price = BigDecimal.valueOf(stringToDouble).setScale(2, RoundingMode.HALF_UP);
        entity.setPrice(price.doubleValue());
        entity.setPaymentLink(photoRequest.getPaymentLink());
    }

    public Photo convertToEntityModify(PhotoRequest photoRequest, Photo entity) throws PhotoException {
        extractedForConvertToEntityModify(photoRequest, entity);
        return entity;
    }

    private static void extractedForConvertToEntityModify(PhotoRequest photoRequest, Photo entity) throws PhotoException {
        validateRequest(photoRequest);
        entity.setCategory(photoRequest.getCategory());
        entity.setAuthor(photoRequest.getAuthor());
        entity.setLocation(photoRequest.getLocation());
        entity.setDescription(photoRequest.getDescription());
        double stringToDouble = Double.parseDouble(photoRequest.getPrice().replace(",", "."));
        BigDecimal price = BigDecimal.valueOf(stringToDouble).setScale(2, RoundingMode.HALF_UP);
        entity.setPrice(price.doubleValue());
        entity.setPaymentLink(photoRequest.getPaymentLink());
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
        response.setCategory(entity.getCategory());
        response.setAuthor(entity.getAuthor());
        response.setLocation(entity.getLocation());
        response.setDescription(entity.getDescription());
        BigDecimal price = BigDecimal.valueOf(entity.getPrice()).setScale(2, RoundingMode.HALF_UP);
        response.setPrice(price.doubleValue());
        response.setPaymentLink(entity.getPaymentLink());
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

    private static void validateRequest(PhotoRequest request) throws PhotoException {
        if (request.getCategory() == null || request.getAuthor() == null || request.getLocation() == null ||
                request.getDescription() == null || request.getPrice() == null || request.getPaymentLink() == null) {
            throw new PhotoException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }
}