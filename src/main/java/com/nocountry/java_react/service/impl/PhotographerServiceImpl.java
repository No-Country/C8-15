package com.nocountry.java_react.service.impl;

import com.nocountry.java_react.dto.request.PhotographerRequest;
import com.nocountry.java_react.dto.response.PhotographerResponse;
import com.nocountry.java_react.mapper.PhotographerMapper;
import com.nocountry.java_react.model.Photographer;
import com.nocountry.java_react.repository.IPhotographerRepository;
import com.nocountry.java_react.service.IPhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotographerServiceImpl implements IPhotographerService {

    private final IPhotographerRepository repository;

    private final PhotographerMapper mapper;

    @Override
    @Transactional
    public PhotographerResponse save(PhotographerRequest request) {
        Photographer entity = new Photographer();
        Photographer entityForConvert = mapper.convertToEntity(entity, request);
        Photographer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public PhotographerResponse modify(String idPhotographer, PhotographerRequest request) {
        Photographer entity = repository.searchById(idPhotographer);
        Photographer entityForConvert = mapper.convertToEntity(entity, request);
        Photographer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public void delete(String idPhotographer) {
        Optional<Photographer> answer = repository.findById(idPhotographer);
        if (answer.isPresent()) {
            Photographer entity = answer.get();
            entity.setDeleted(!entity.isDeleted());
            entity.setUpdated(new Date());
            repository.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PhotographerResponse getById(String idPhotographer) {
        if (repository.existsById(idPhotographer)) {
            Photographer entity = repository.searchById(idPhotographer);
            return mapper.convertToResponse(entity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotographerResponse> getAll() {
        List<Photographer> photographerList = repository.findAll();
        return mapper.convertToResponseList(photographerList);
    }
}