package com.nocountry.java_react.auth.service.impl;

import com.nocountry.java_react.auth.dto.request.UserAuthenticationRequest;
import com.nocountry.java_react.auth.mapper.UserAuthenticationMapper;
import com.nocountry.java_react.auth.model.UserAuthentication;
import com.nocountry.java_react.auth.repository.IUserAuthenticationRepository;
import com.nocountry.java_react.auth.service.IUserAuthenticationService;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.RegisterException;
import com.nocountry.java_react.exception.UserAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements IUserAuthenticationService, UserDetailsService {

    private final IUserAuthenticationRepository repository;
    private final UserAuthenticationMapper userAuthenticationMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuthentication userAuthentication = repository.findUserByEmail(username);
        if (userAuthentication == null) {
            throw new RegisterException(EExceptionMessage.USERNAME_OR_PASSWORD_NOT_FOUND.toString());
        }
        List<GrantedAuthority> permissions = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + userAuthentication.getRole());
        permissions.add(grantedAuthority);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("userSession", userAuthentication);
        return new User(userAuthentication.getEmail(), userAuthentication.getPassword(), permissions);
    }

    public Boolean ifUsernameExist(String username) {
        return repository.findUserByEmail(username) != null;
    }

    @Override
    @Transactional
    public UserAuthentication register(UserAuthenticationRequest request) throws UserAuthenticationException, EmailAlreadyExistException {

        if (ifUsernameExist(request.getEmail())) {
            throw new RegisterException(EExceptionMessage.EMAIL_ALREADY_EXISTS.toString());
        }
        UserAuthentication userAuthentication = new UserAuthentication();
        UserAuthentication userAuthenticationForConvert = userAuthenticationMapper.covertToEntity(userAuthentication, request);
        UserAuthentication entityForSave = repository.save(userAuthenticationForConvert);
        return repository.save(entityForSave);
    }
}