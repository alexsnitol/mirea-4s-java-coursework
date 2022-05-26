package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.AdvertisedPhoto;
import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.repository.AdvertisedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Service
@Scope("singleton")
public class AdvertisedServiceImpl extends AbstractServiceImpl<Advertised, AdvertisedRepository> {

    @Autowired
    AdvertisedRepository advertisedRepository;
    @Autowired
    @Lazy
    EstateObjectServiceImpl estateObjectService;
    @Autowired
    @Lazy
    FavoriteServiceImpl favoriteService;
    @Autowired
    @Lazy
    AdvertisedPhotoServiceImpl advertisedPhotoService;

    @PostConstruct
    public void init() {
        defaultRepository = advertisedRepository;
    }

    @Override
    public void save(Advertised advertised) {
        estateObjectService.save(advertised.getEstateObject());
        defaultRepository.save(advertised);
    }

    @Override
    public void deleteById(Long id) {
        List<Favorite> favoriteList = favoriteService.getAllByAdvertisedId(id);

        if (!favoriteList.isEmpty()) {
            for (Favorite favorite : favoriteList) {
                favoriteService.delete(favorite);
            }
        }

        List<AdvertisedPhoto> advertisedPhotoList = advertisedPhotoService.getAllByAdvertisedId(id);

        if (!advertisedPhotoList.isEmpty()) {
            for (AdvertisedPhoto advertisedPhoto : advertisedPhotoList) {
                if (advertisedPhoto.getForm() == null) {
                    advertisedPhotoService.delete(advertisedPhoto);
                } else {
                    advertisedPhoto.setAdvertised(null);
                    advertisedPhotoService.save(advertisedPhoto);
                }
            }
        }

        advertisedRepository.deleteById(id);
    }

    public List<Advertised> getAllByEstateObjectId(Long id) {
        return advertisedRepository.findAllByEstateObjectId(id);
    }
}
