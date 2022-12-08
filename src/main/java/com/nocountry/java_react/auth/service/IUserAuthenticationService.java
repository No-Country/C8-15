package com.nocountry.java_react.auth.service;

import com.nocountry.java_react.auth.dto.request.UserAuthenticationRequest;
import com.nocountry.java_react.auth.model.UserAuthentication;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.UserAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IUserAuthenticationService {
    @Transactional
    UserAuthentication register(UserAuthenticationRequest request) throws UserAuthenticationException, EmailAlreadyExistException;
}
