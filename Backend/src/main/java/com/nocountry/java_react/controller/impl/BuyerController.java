package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IBuyerController;
import com.nocountry.java_react.commons.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.BUYER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BuyerController implements IBuyerController {
}