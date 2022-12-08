package com.nocountry.java_react.auth.mapper;

import com.nocountry.java_react.auth.dto.request.UserAuthenticationRequest;
import com.nocountry.java_react.auth.model.UserAuthentication;
import com.nocountry.java_react.auth.repository.IUserAuthenticationRepository;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.UserAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationMapper {

    private final BCryptPasswordEncoder passwordEncoder;
    private final IUserAuthenticationRepository repository;

    private static void validateRequest(UserAuthenticationRequest request) throws UserAuthenticationException {
        if ((request.getName() == null) || (request.getSurname() == null) || (request.getEmail() == null) ||
                (request.getPassword() == null) || (request.getConfirmPassword() == null)) {
            throw new UserAuthenticationException(EExceptionMessage.REQUEST_WRONG_DATA.toString());
        }
    }

    private static void passwordMatch(UserAuthenticationRequest request) throws UserAuthenticationException {
        if (!((request.getPassword() != null) && (request.getConfirmPassword() != null)
                && request.getConfirmPassword().equals(request.getPassword()))) {
            throw new UserAuthenticationException(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString());
        }
    }

    public UserAuthentication covertToEntity(UserAuthentication entity, UserAuthenticationRequest request) throws EmailAlreadyExistException, UserAuthenticationException {
        validateRequest(request);
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        validateEmail(request);
        entity.setEmail(request.getEmail());
        passwordMatch(request);
        entity.setPassword(encryptPassword(request.getPassword()));
        //entity.setRole(role);
        return entity;
    }

    public String encryptPassword(String password) {
        password = passwordEncoder.encode(password);
        return password;
    }

    private void validateEmail(UserAuthenticationRequest request) throws EmailAlreadyExistException {
        boolean existMail = repository.existsByEmail(request.getEmail());
        if (existMail) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        }
    }
}