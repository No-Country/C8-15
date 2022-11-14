package com.noCountry.C815MJavaReact.controller.impl;

import com.noCountry.C815MJavaReact.controller.IPhotoController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.noCountry.C815MJavaReact.commons.constants.Constants.PHOTO_URI;

@RestController
@RequestMapping(PHOTO_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController implements IPhotoController {
}