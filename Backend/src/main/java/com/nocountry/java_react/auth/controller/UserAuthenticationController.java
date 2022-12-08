package com.nocountry.java_react.auth.controller;

import com.nocountry.java_react.auth.dto.request.AuthenticationRequest;
import com.nocountry.java_react.auth.dto.request.UserAuthenticationRequest;
import com.nocountry.java_react.auth.dto.response.AuthenticationResponse;
import com.nocountry.java_react.auth.service.JwtUtilsService;
import com.nocountry.java_react.auth.service.impl.UserAuthenticationServiceImpl;
import com.nocountry.java_react.commons.constants.Constants;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.AUTH_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final UserAuthenticationServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilsService jwtTokenUtil;

    @PostMapping(path = "/sing-up")
    public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserAuthenticationRequest request) throws Exception {
        userDetailsService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/sing-in")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
            userDetails = (UserDetails) authentication.getPrincipal();
        } catch (BadCredentialsException exception) {
            throw new Exception(EExceptionMessage.USERNAME_OR_PASSWORD_NOT_FOUND.toString());
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}