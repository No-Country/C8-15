package com.nocountry.java_react.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.commons.enums.EPathUpload;
import com.nocountry.java_react.dto.request.PhotoRequest;
import com.nocountry.java_react.dto.request.buyer.BuyerRequestBuyPhoto;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.nocountry.java_react.commons.enums.EPathUpload.PATH_BUYER_IMAGE;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements IBuyerService {

    private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
    private static final String TRUE = "true";
    private static final Path ORIGIN_PATH = Paths.get(EPathUpload.ORIGIN_PATH.toString());
    private static final Path DESTINY_PATH = Paths.get(EPathUpload.DESTINY_PATH.toString());
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
    public void addProfilePictureToBuyer(String idBuyer, MultipartFile profilePicture) throws PhotoException, BuyerException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            if (buyer.getIdProfilePicture() != null) {
                String idPhoto = buyer.getIdProfilePicture();
                logger.info("ID PHOTO : {}", idPhoto);
                photoService.deletePhotoById(idPhoto, pathFolderUpload);
                Photo saveProfilePicture = photoService.saveProfilePicture(profilePicture, pathFolderUpload, pathFileUpload);
                buyer.setIdProfilePicture(saveProfilePicture.getId());
                buyer.setProfilePicture(saveProfilePicture.getPath());
            } else {
                Photo saveProfilePicture = photoService.saveProfilePicture(profilePicture, pathFolderUpload, pathFileUpload);
                buyer.setIdProfilePicture(saveProfilePicture.getId());
                buyer.setProfilePicture(saveProfilePicture.getPath());
            }
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }

    @Override
    public void removeProfilePictureToBuyer(String idBuyer) throws BuyerException, PhotoException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            if (buyer.getIdProfilePicture() != null) {
                String idPhoto = buyer.getIdProfilePicture();
                logger.info("ID PHOTO : {}", idPhoto);
                photoService.deletePhotoById(idPhoto, pathFolderUpload);
                buyer.setIdProfilePicture(null);
                buyer.setProfilePicture(null);
            } else {
                throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
            }
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

    public static String getPhotoExtension(String originalName) {
        return originalName.substring(originalName.lastIndexOf("."));
    }

    private void addPhotoToBuyer(Buyer buyer, String stringRequest, MultipartFile photo) throws PhotoException {
        PhotoRequest photoRequest = new PhotoRequest();
        try {
            photoRequest = new ObjectMapper().readValue(stringRequest, PhotoRequest.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Photo savePhoto = photoService.savePhoto(photoRequest, photo, pathFolderUpload, pathFileUpload);

        savePhoto.getBuyers().add(buyer);
        buyer.getPhotos().add(savePhoto);
    }

    @Override
    @Transactional
    public void buyPhoto(String idBuyer, String idPhoto, BuyerRequestBuyPhoto request) throws BuyerException, PhotoException, IOException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            Optional<Photo> optionalPhoto = photoRepository.findById(idPhoto);
            if (optionalPhoto.isPresent()) {
                Photo photo = photoRepository.getReferenceById(idPhoto);

                Photo newPhoto = new Photo();

                logger.info("ID PHOTO : {}", photo.getId());
                logger.info("ID NEW PHOTO : {}", newPhoto.getId());

                if (request.getPurchasedPhoto().equalsIgnoreCase(TRUE)) {
                    photo.setSales(photo.getSales() + 1);

                    List<Photo> photoList = buyer.getPhotos();
                    newPhoto.setId(UUID.randomUUID().toString());
                    newPhoto.setSales(0);
                    newPhoto.setPhotographer(null);
                    newPhoto.setPrice(null);
                    newPhoto.setPaymentLink(null);
                    newPhoto.setPath(PATH_BUYER_IMAGE + photo.getFileName());
                    newPhoto.setUpdated(new Date());
                    newPhoto.setAuthor(photo.getAuthor());
                    newPhoto.setCategory(photo.getCategory());
                    newPhoto.setDescription(photo.getDescription());
                    newPhoto.setLocation(photo.getLocation());
                    newPhoto.setOriginalName(photo.getOriginalName());

                    // COPY NEW PHOTO TO BUYER FOLDER - DOWNLOADED PHOTO

                    Path originPath = Paths.get("%s\\%s".formatted(ORIGIN_PATH, photo.getFileName()));

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
                    String stringDate = sdf.format(new Date());
                    String newPhotoName = photo.getOriginalName().replaceAll(getPhotoExtension(photo.getOriginalName()),
                            " - " + stringDate + getPhotoExtension(photo.getOriginalName()));
                    Path destinyPath = Paths.get("%s\\%s".formatted(DESTINY_PATH, newPhotoName));
                    Files.copy(originPath, destinyPath/*, StandardCopyOption.REPLACE_EXISTING*/);

                    // END COPY PHOTO

                    newPhoto.setFileName(newPhotoName);

                    photoList.add(newPhoto);
                    logger.info("PHOTO LIST : {}", photoList.size());
                    logger.info("ID NEW PHOTO : {}", newPhoto.getId());
                    buyer.setPhotos(photoList);

                    newPhoto.getBuyers().add(buyer);
                    repository.save(buyer);

                } else {
                    throw new BuyerException(EExceptionMessage.YOU_MUST_FIRST_PURCHASE_THE_PHOTO_TO_DOWNLOAD_IT.toString());
                }
            } else {
                throw new PhotoException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
            }
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
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

    @Override
    @Transactional
    public Resource downloadPhoto(String idBuyer, String idPhoto) throws PhotoException, BuyerException, MalformedURLException {
        Optional<Buyer> optionalBuyer = repository.findById(idBuyer);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = repository.getReferenceById(idBuyer);
            List<Photo> photoList = buyer.getPhotos();
            Photo photoById = photoService.getPhotoById(idPhoto);
            boolean exists = false;
            for (Photo photo : photoList) {
                logger.info("LISTA DE FOTOS : {}", photo.getId());
                if (photo.getId().equals(photoById.getId())) {
                    exists = true;
                    logger.info("EXIST " + true);
                    break;
                }
            }
            if (exists) {
                return photoService.downloadPhoto(idPhoto, pathFolderUpload);
            } else {
                throw new MalformedURLException(EExceptionMessage.PHOTO_NOT_FOUND.toString());
            }
        } else {
            throw new BuyerException(EExceptionMessage.BUYER_NOT_FOUND.toString());
        }
    }
}