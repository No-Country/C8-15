package com.noCountry.C815MJavaReact.controller.impl;

import com.noCountry.C815MJavaReact.controller.IBuyerController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.noCountry.C815MJavaReact.commons.constants.Constants.BUYER_URI;

@RestController
@RequestMapping(BUYER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BuyerController implements IBuyerController {
}