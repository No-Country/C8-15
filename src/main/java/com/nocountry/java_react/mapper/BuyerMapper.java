package com.nocountry.java_react.mapper;

import com.nocountry.java_react.commons.enums.EUserRole;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.model.Buyer;
import com.nocountry.java_react.repository.IBuyerRepository;
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
public class BuyerMapper {

    private static final EUserRole role = EUserRole.BUYER;
    private final PhotoMapper photoMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IBuyerRepository repository;
    private final MessageSource messageSource;

    public Buyer convertToEntity(Buyer entity, BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException {
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
            throw new BuyerException(messageSource.getMessage("passwords.do.not.match", null, Locale.ENGLISH));
        }
        entity.setRole(role);
        return entity;
    }

    public Buyer convertToEntityModify(Buyer entity, BuyerRequestModify request)
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

    private static void extractedForConvertToEntityModifyBasic(Buyer entity, BuyerRequestModify request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        entity.setUpdated(new Date());
    }

    private static void extractedForConvertToEntityModifyFull(Buyer entity, BuyerRequestModify request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getSurname() != null) entity.setSurname(request.getSurname());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getTelephone() != null) entity.setTelephone(request.getTelephone());
        if (request.getClass() != null) entity.setCity(request.getCity());
        if (request.getCountry() != null) entity.setCountry(request.getCountry());
        entity.setUpdated(new Date());
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

        // LIST OF PHOTOS
        List<PhotoResponse> photoList = new ArrayList<>(photoMapper.convertToResponseList(entity.getPhotos()));
        response.setPhotos(photoList);

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

    public String encryptPassword(String password) {
        password = passwordEncoder.encode(password);
        return password;
    }

    public Buyer convertToEntityModifyPassword(Buyer entity, BuyerRequestPassword request) throws BuyerException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());
        if (passwordChecker) {
            if (request.getPassword() != null) {
                if (request.getConfirmPassword() != null && request.getConfirmPassword().equals(request.getPassword())) {
                    entity.setPassword(encryptPassword(request.getPassword()));
                } else {
                    throw new BuyerException(messageSource.getMessage("passwords.do.not.match", null, Locale.ENGLISH));
                }
            }
        } else {
            throw new BuyerException(messageSource.getMessage("wrong.password", null, Locale.ENGLISH));
        }
        return entity;
    }
}