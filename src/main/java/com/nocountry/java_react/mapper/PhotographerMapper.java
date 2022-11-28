package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.commons.enums.EUserRole;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestCreate;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestModify;
import com.nocountry.java_react.dto.request.photographer.PhotographerRequestPassword;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotographerException;
import com.nocountry.java_react.model.Photographer;
import com.nocountry.java_react.repository.IPhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotographerMapper {

    private static final EUserRole role = EUserRole.PHOTOGRAPHER;
    private final PhotoMapper photoMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IPhotographerRepository repository;

    public Photographer convertToEntity(Photographer entity, PhotographerRequestCreate request) throws EmailAlreadyExistException, PhotographerException {
        validateRequest(request);
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        validateEmail(request);
        entity.setEmail(request.getEmail());
        passwordMatch(request);
        entity.setPassword(encryptPassword(request.getPassword()));
        entity.setRole(role);
        return entity;
    }

    public Photographer convertToEntityModify(Photographer entity, PhotographerRequestModify request) throws EmailAlreadyExistException, PhotographerException {
        validateRequestModify(request);
        String requestEmail = request.getEmail();
        String entityEmail = entity.getEmail();
        boolean existMail = repository.existsByEmail(request.getEmail());
        if (existMail && requestEmail.equals(entityEmail)) {

            forConvertToEntityModifyBasic(entity, request);
        } else if (existMail) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        } else {
            forConvertToEntityModifyFull(entity, request);
        }
        return entity;
    }

    private static void forConvertToEntityModifyBasic(Photographer entity, PhotographerRequestModify request) {
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setTelephone(request.getTelephone());
        entity.setCity(request.getCity());
        entity.setCountry(request.getCountry());
        entity.setFacebookUrl(request.getFacebookUrl());
        entity.setInstagramUrl(request.getInstagramUrl());
        entity.setUpdated(new Date());
    }

    private static void forConvertToEntityModifyFull(Photographer entity, PhotographerRequestModify request) {
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setTelephone(request.getTelephone());
        entity.setCity(request.getCity());
        entity.setCountry(request.getCountry());
        entity.setFacebookUrl(request.getFacebookUrl());
        entity.setInstagramUrl(request.getInstagramUrl());
        entity.setUpdated(new Date());
    }

    public Photographer convertToEntityModifyPassword(Photographer entity, PhotographerRequestPassword request) throws PhotographerException {
        validateRequestPassword(request);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());
        if (passwordChecker) {
            if (request.getPassword() != null) {
                if (request.getConfirmPassword() != null && request.getConfirmPassword().equals(request.getPassword())) {
                    entity.setPassword(encryptPassword(request.getPassword()));
                } else {
                    throw new PhotographerException(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString());
                }
            }
        } else {
            throw new PhotographerException(EExceptionMessage.WRONG_PASSWORD.toString());
        }
        return entity;
    }

    public PhotographerResponse convertToResponse(Photographer entity) {
        PhotographerResponse response = new PhotographerResponse();
        response.setIdPhotographer(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setEmail(entity.getEmail());
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

    public String encryptPassword(String password) {
        password = passwordEncoder.encode(password);
        return password;
    }

    private static void validateRequest(PhotographerRequestCreate request) throws PhotographerException {
        if (request.getName() == null || request.getSurname() == null || request.getEmail() == null ||
                request.getPassword() == null || request.getConfirmPassword() == null) {
            throw new PhotographerException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    private static void validateRequestModify(PhotographerRequestModify request) throws PhotographerException {
        if (request.getName() == null || request.getSurname() == null || request.getEmail() == null ||
                request.getTelephone() == null || request.getCity() == null || request.getCountry() == null ||
                request.getFacebookUrl() == null || request.getInstagramUrl() == null) {
            throw new PhotographerException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    private static void validateRequestPassword(PhotographerRequestPassword request) throws PhotographerException {
        if (request.getOldPassword() == null || request.getPassword() == null || request.getConfirmPassword() == null) {
            throw new PhotographerException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    private static void passwordMatch(PhotographerRequestCreate request) throws PhotographerException {
        if (!(request.getPassword() != null && request.getConfirmPassword() != null
                && request.getConfirmPassword().equals(request.getPassword()))) {
            throw new PhotographerException(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString());
        }
    }

    private void validateEmail(PhotographerRequestCreate request) throws EmailAlreadyExistException {
        boolean existMail = repository.existsByEmail(request.getEmail());
        if (existMail) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        }
    }
}