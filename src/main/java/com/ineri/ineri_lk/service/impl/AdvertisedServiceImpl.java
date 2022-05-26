package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.AdvertisedPhoto;
import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.repository.AdvertisedRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    EstateObjectServiceImpl estateObjectService;
    @Autowired
    FavoriteServiceImpl favoriteService;
    @Autowired
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
                favoriteService.deleteById(favorite.getId());
            }
        }

        List<AdvertisedPhoto> advertisedPhotoList = advertisedPhotoService.getAllByAdvertisedId(id);

        if (!advertisedPhotoList.isEmpty()) {
            for (AdvertisedPhoto advertisedPhoto : advertisedPhotoList) {
                advertisedPhotoService.deleteById(advertisedPhoto.getId());
            }
        }

        advertisedRepository.deleteById(id);
    }

}
