package com.nocountry.java_react.mapper;

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
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PhotographerMapper {

    private static final EUserRole role = EUserRole.PHOTOGRAPHER;
    private final PhotoMapper photoMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IPhotographerRepository repository;
    private final MessageSource messageSource;

    public Photographer convertToEntity(Photographer entity, PhotographerRequestCreate request) throws EmailAlreadyExistException, PhotographerException {
        boolean existMail = repository.existsByEmail(request.getEmail());
        if (existMail) {
            throw new EmailAlreadyExistException(messageSource.getMessage("email.already.exists", null, Locale.ENGLISH));
        }
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getPassword() != null && request.getConfirmPassword() != null
                && request.getConfirmPassword().equals(request.getPassword())) {
            entity.setPassword(encryptPassword(request.getPassword()));
        } else {
            throw new PhotographerException(messageSource.getMessage("passwords.do.not.match", null, Locale.ENGLISH));
        }
        entity.setRole(role);
        return entity;
    }

    public Photographer convertToEntityModify(Photographer entity, PhotographerRequestModify request)
            throws EmailAlreadyExistException {
        String requestEmail = request.getEmail();
        String entityEmail = entity.getEmail();
        boolean existMail = repository.existsByEmail(requestEmail);
        if (existMail && requestEmail.equals(entityEmail)) {
            extractedForConvertToEntityModifyBasic(entity, request);
        } else if (existMail) {
            throw new EmailAlreadyExistException(messageSource.getMessage("email.already.exists", null, Locale.ENGLISH));
        } else {
            extractedForConvertToEntityModifyFull(entity, request);
        }
        return entity;
    }

    private static void extractedForConvertToEntityModifyBasic(Photographer entity, PhotographerRequestModify request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        if (request.getFacebookUrl() != null) entity.setFacebookUrl(request.getFacebookUrl());
        if (request.getInstagramUrl() != null) entity.setInstagramUrl(request.getInstagramUrl());
        entity.setUpdated(new Date());
    }

    private static void extractedForConvertToEntityModifyFull(Photographer entity, PhotographerRequestModify request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        if (request.getFacebookUrl() != null) entity.setFacebookUrl(request.getFacebookUrl());
        if (request.getInstagramUrl() != null) entity.setInstagramUrl(request.getInstagramUrl());
        entity.setUpdated(new Date());
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

    public Photographer convertToEntityModifyPassword(Photographer entity, PhotographerRequestPassword request) throws PhotographerException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());
        if (passwordChecker) {
            if (request.getPassword() != null) {
                if (request.getConfirmPassword() != null && request.getConfirmPassword().equals(request.getPassword())) {
                    entity.setPassword(encryptPassword(request.getPassword()));
                } else {
                    throw new PhotographerException(messageSource.getMessage("passwords.do.not.match", null, Locale.ENGLISH));
                }
            }
        } else {
            throw new PhotographerException(messageSource.getMessage("wrong.password", null, Locale.ENGLISH));
        }
        return entity;
    }
}