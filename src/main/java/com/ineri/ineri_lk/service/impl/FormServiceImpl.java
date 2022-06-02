package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.AdvertisedPhoto;
import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.model.Form;
import com.ineri.ineri_lk.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Service
public class FormServiceImpl {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    @Lazy
    private AdvertisedPhotoServiceImpl advertisedPhotoService;

    public void save(Form form) {
        formRepository.save(form);
    }

    public List<Form> getAll() {
        return formRepository.findAll();
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

}
