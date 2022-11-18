package com.nocountry.java_react.service.impl;

import com.nocountry.java_react.dto.request.BuyerRequest;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.mapper.BuyerMapper;
import com.nocountry.java_react.model.Buyer;
import com.nocountry.java_react.repository.IBuyerRepository;
import com.nocountry.java_react.service.IBuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements IBuyerService {

    private final IBuyerRepository repository;

    private final BuyerMapper mapper;
    @Override
    @Transactional
    public BuyerResponse save(BuyerRequest request) {
        Buyer entity = new Buyer();
        Buyer entityForConvert = mapper.convertToEntity(entity, request);
        Buyer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public BuyerResponse modify(String idBuyer, BuyerRequest request) {
        Buyer entity = repository.searchById(idBuyer);
        Buyer entityForConvert = mapper.convertToEntity(entity, request);
        Buyer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public void delete(String idBuyer) {
        Optional<Buyer> answer = repository.findById(idBuyer);
        if (answer.isPresent()) {
            Buyer entity = answer.get();
            entity.setDeleted(!entity.isDeleted());
            entity.setUpdated(new Date());
            repository.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BuyerResponse getById(String idBuyer) {
        if (repository.existsById(idBuyer)) {
            Buyer entity = repository.searchById(idBuyer);
            return mapper.convertToResponse(entity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuyerResponse> getAll() {
        List<Buyer> buyerList = repository.findAll();
        return mapper.convertToResponseList(buyerList);
    }
}