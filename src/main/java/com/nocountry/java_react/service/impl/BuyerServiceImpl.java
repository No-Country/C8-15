package com.nocountry.java_react.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestCreate;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestModify;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestPassword;
import com.nocountry.java_react.dto.response.BuyerResponse;
import com.nocountry.java_react.exception.BuyerException;
import com.nocountry.java_react.exception.EmailAlreadyExistException;
import com.nocountry.java_react.exception.PhotoException;
import com.nocountry.java_react.mapper.BuyerMapper;
import com.nocountry.java_react.model.Buyer;
import com.nocountry.java_react.model.Photo;
import com.nocountry.java_react.repository.IBuyerRepository;
import com.nocountry.java_react.repository.IPhotoRepository;
import com.nocountry.java_react.service.IBuyerService;
import com.nocountry.java_react.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements IBuyerService {

    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_BUYER_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_BUYER_IMAGE.toString();
    private final IBuyerRepository repository;
    private final BuyerMapper mapper;
    private final IPhotoService photoService;
    private final IPhotoRepository photoRepository;

    @Override
    @Transactional
    public BuyerResponse saveBuyer(BuyerRequestCreate request) throws EmailAlreadyExistException, BuyerException {
        Buyer entity = new Buyer();
        Buyer entityForConvert = mapper.convertToEntity(entity, request);
        Buyer entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    @Transactional
    public BuyerResponse modifyBuyer(String idBuyer, BuyerRequestModify request) throws EmailAlreadyExistException, BuyerException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer entity = optionalBuyer.get();
            Buyer entityForConvert = mapper.convertToEntityModify(entity, request);
            Buyer entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional
    public BuyerResponse modifyPassword(String idBuyer, BuyerRequestPassword request) throws BuyerException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = optionalBuyer.get();
            Buyer entityForConvert = mapper.convertToEntityModifyPassword(buyer, request);
            Buyer entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional
    public void deleteBuyer(String idBuyer) throws BuyerException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer entity = optionalBuyer.get();
            entity.setDeleted(!entity.isDeleted());
            entity.setUpdated(new Date());
            repository.save(entity);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BuyerResponse getBuyerById(String idBuyer) throws BuyerException {
        if (repository.existsById(idBuyer)) {
            Buyer entity = repository.getReferenceById(idBuyer);
            return mapper.convertToResponse(entity);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuyerResponse> getAllBuyer() throws BuyerException {
        List<Buyer> buyerList = repository.findAll();
        if (!buyerList.isEmpty()) {
            return mapper.convertToResponseList(buyerList);
        } else {
            throw new BuyerException(EExceptionMessage.THE_LIST_OF_BUYERS_IS_EMPTY.toString());
        }
    }

    @Override
    @Transactional
    public void addPhotoToBuyer(String idBuyer, String stringRequest, MultipartFile photo) throws BuyerException, PhotoException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            // ADD PHOTOS TO BUYER
            addPhotoToBuyer(buyer, stringRequest, photo);
            repository.save(buyer);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    private void addPhotoToBuyer(Buyer buyer, String stringRequest, MultipartFile photo) throws PhotoException {
        PhotoRequest photoRequest = new PhotoRequest();
        try {
            photoRequest = new ObjectMapper().readValue(stringRequest, PhotoRequest.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Photo savePhoto = photoService.savePhoto(photoRequest, photo, pathFolderUpload, pathFileUpload);

        savePhoto.setBuyer(buyer);
        buyer.getPhotos().add(savePhoto);
    }

    @Override
    @Transactional
    public void removePhotoToBuyer(String idBuyer, String idPhoto) throws PhotoException, BuyerException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            Optional<Photo> optionalPhoto = photoRepository.findById(idPhoto);
            if (optionalPhoto.isPresent()) {
                Photo photo = photoRepository.getReferenceById(idPhoto);
                List<Photo> photoList = buyer.getPhotos();
                photoList.remove(photo);
                buyer.setPhotos(photoList);
                photoService.deletePhotoById(idPhoto, pathFolderUpload);
                repository.save(buyer);
            } else {
                throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
            }
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    @Transactional
    public void removeAllPhotosToBuyer(String idBuyer) throws BuyerException, PhotoException {
        Optional<Buyer> optionalPhotographer = repository.findById(idBuyer);
        if (optionalPhotographer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            List<Photo> photoList = buyer.getPhotos();
            for (Photo photo : photoList) {
                photoService.deletePhotoById(photo.getId(), pathFolderUpload);
            }
            photoList.clear();
            buyer.setPhotos(photoList);
            repository.save(buyer);
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }
}