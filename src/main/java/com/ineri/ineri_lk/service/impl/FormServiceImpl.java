package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.*;
import com.ineri.ineri_lk.repository.AdvertisedRepository;
import com.ineri.ineri_lk.repository.AdvertisedStatusRepository;
import com.ineri.ineri_lk.repository.EstateObjectRepository;
import com.ineri.ineri_lk.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Service
public class FormServiceImpl {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private AdvertisedRepository advertisedRepository;

    @Autowired
    private EstateObjectRepository estateObjectRepository;

    @Autowired
    private AdvertisedStatusRepository advertisedStatusRepository;

    @Autowired
    @Lazy
    private AdvertisedPhotoServiceImpl advertisedPhotoService;

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


    public void publish(Form form) {
        Advertised advertised = new Advertised();

        EstateObject estateObject = fillEstateObject(form);
        estateObjectRepository.save( estateObject);
        advertised.setEstateObject(estateObject);

        advertised.setDescription(form.getDescription());
        advertised.setPrice(form.getPrice());
        advertised.setAdvertisedStatus(advertisedStatusRepository.getById(1L));
        advertised.setDateTimeCreated(LocalDateTime.now());

        //временно
        advertised.setAdvertisedPhoto(null);

        //удалить анкету после публикации
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

}
