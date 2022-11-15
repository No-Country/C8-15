package com.nocountry.java_react.service.impl;

import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.response.PhotoResponse;
import com.nocountry.java_react.mapper.PhotoMapper;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements IPhotoService {

    private final IPhotoRepository repository;

    private final PhotoMapper mapper;

    @Override
    @Transactional
    public PhotoResponse save(PhotoRequest request) {
        Photo entity = new Photo();
        Photo entityForConvert = mapper.convertToEntity(entity, request);
        Photo entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public PhotoResponse modify(String idPhoto, PhotoRequest request) {
        Photo entity = repository.searchById(idPhoto);
        Photo entityForConvert = mapper.convertToEntity(entity, request);
        Photo entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public void delete(String idPhoto) {
        Optional<Photo> answer = repository.findById(idPhoto);
        if (answer.isPresent()) {
            Photo entity = answer.get();
            entity.setSoftDelete(!entity.isSoftDelete());
            entity.setModificationDate(new Date());
            repository.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PhotoResponse getById(String idPhoto) {
        if (repository.existsById(idPhoto)) {
            Photo entity = repository.searchById(idPhoto);
            return mapper.convertToResponse(entity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotoResponse> getAll() {
        List<Photo> buyerList = repository.findAll();
        return mapper.convertToResponseList(buyerList);
    }
}