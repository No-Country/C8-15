package com.nocountry.java_react.controller.impl;

import com.nocountry.java_react.controller.IPhotoController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nocountry.java_react.commons.constants.Constants.PHOTO_URI;

@RestController
@RequestMapping(PHOTO_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController implements IPhotoController {
}