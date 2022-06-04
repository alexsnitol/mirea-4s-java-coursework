package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.AdvertisedPhoto;
import com.ineri.ineri_lk.repository.AdvertisedPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AdvertisedPhotoServiceImpl extends AbstractServiceImpl<AdvertisedPhoto, AdvertisedPhotoRepository> {

    @Autowired
    AdvertisedPhotoRepository advertisedPhotoRepository;

    @PostConstruct
    public void init() {
        defaultRepository = advertisedPhotoRepository;
    }

    public List<AdvertisedPhoto> getAllByAdvertisedId(Long id) {
        return advertisedPhotoRepository.findAllByAdvertisedId(id);
    }

    public List<AdvertisedPhoto> getAllByFormId(Long id) {
        return advertisedPhotoRepository.findAllByFormId(id);
    }

    public void saveAdvertisedPhoto(Advertised advertised, String path) {
        AdvertisedPhoto advertisedPhoto = new AdvertisedPhoto();
        advertisedPhoto.setAdvertised(advertised);
        advertisedPhoto.setPath(path);
        advertisedPhotoRepository.save(advertisedPhoto);
    }

}
