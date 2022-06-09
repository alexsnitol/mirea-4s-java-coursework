package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.*;
import com.ineri.ineri_lk.repository.*;
import com.ineri.ineri_lk.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Kozlov Alexander
 */

@Service
public class FormServiceImpl {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisedRepository advertisedRepository;
    @Autowired
    private EstateObjectRepository estateObjectRepository;
    @Autowired
    private AdvertisedStatusRepository advertisedStatusRepository;
    @Autowired
    @Lazy
    private AdvertisedPhotoServiceImpl advertisedPhotoService;
    @Autowired
    private AdvertisedPhotoRepository advertisedPhotoRepository;


    public void save(Form form) {
        formRepository.save(form);
    }

    public List<Form> getAll() {
        return formRepository.findAll();
    }

    public List<Form> getAllByUsername(String username) {
        return formRepository.findAllByUser_Username(username);
    }

    public Form getById(Long id) {
        return formRepository.getById(id);
    }

    public void deleteById(Long id) {
        List<AdvertisedPhoto> advertisedPhotoList = advertisedPhotoService.getAllByFormId(id);

        if (!advertisedPhotoList.isEmpty()) {
            for (AdvertisedPhoto advertisedPhoto : advertisedPhotoList) {
                if (advertisedPhoto.getAdvertised() == null) {
                    advertisedPhotoService.deleteById(advertisedPhoto.getId());
                } else {
                    advertisedPhoto.setForm(null);
                    advertisedPhotoService.save(advertisedPhoto);
                }
            }
        }

        formRepository.deleteById(id);
    }

    public List<Form> getAllByAddressId(Long id) {
        return formRepository.findAllByAddressId(id);
    }


    public void publish(List<MultipartFile> multipartFileList, Form form, String adminName) {
        Advertised advertised = new Advertised();

        EstateObject estateObject = fillEstateObject(form);
        estateObjectRepository.save( estateObject);
        advertised.setEstateObject(estateObject);

        advertised.setDescription(form.getDescription());
        advertised.setPrice(form.getPrice());
        advertised.setAdvertisedStatus(advertisedStatusRepository.getById(1L));
        advertised.setDateTimeCreated(LocalDateTime.now());
        Optional<User> admin = userRepository.findByUsername(adminName);
        admin.ifPresent(advertised::setAdmin);
        advertised.setUser(form.getUser());

        if (!multipartFileList.get(0).getResource().getFilename().equals("")) {
            String fileName;
            String uploadDir = "src/main/resources/upload/catalog";
            int i = advertised.getAdvertisedPhoto().size();

            try {
                for (MultipartFile file : multipartFileList) {
                    fileName = advertised.getId() + "-" + i++ + ".jpg";
                    FileUploadUtil.saveFile(uploadDir, fileName, file);
                    advertisedPhotoService.saveAdvertisedPhoto(advertised, "/upload/catalog/" + fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        advertisedRepository.save(advertised);
    }

    private static EstateObject fillEstateObject(Form form) {
        EstateObject estateObject = new EstateObject();
        estateObject.setArea(form.getArea());
        estateObject.setFloor(form.getFloor());
        estateObject.setMaxFloor(form.getMaxFloor());
        estateObject.setRoomSize(form.getRoomSize());
        estateObject.setDateTimeCreated(LocalDateTime.now());
        estateObject.setHouseType(form.getHouseType());
        estateObject.setPropertyType(form.getPropertyType());
        estateObject.setRenovationType(form.getRenovationType());
        estateObject.setEstateObjectType(form.getEstateObjectType());
        estateObject.setAddress(form.getAddress());
        return estateObject;
    }

    public void saveFormPhoto(Form form, String path) {
        AdvertisedPhoto advertisedPhoto = new AdvertisedPhoto();
        advertisedPhoto.setForm(form);
        advertisedPhoto.setPath(path);
        if (!advertisedPhotoRepository.exists(Example.of(advertisedPhoto))) {
            advertisedPhotoRepository.save(advertisedPhoto);
        }
    }

}
