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
import java.util.stream.Collectors;

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

    public List<Advertised> getAllFiltered(String cityName,
                                           Float areaMin, Float areaMax,
                                           Integer floorMin, Integer floorMax,
                                           Integer houseFloorMin, Integer houseFloorMax,
                                           Integer roomSizeMin, Integer roomSizeMax,
                                           String houseTypeName,
                                           String propertyTypeName,
                                           String renovationTypeName,
                                           String estateObjectTypeName) {

        List<Advertised> advertisedList = getAll();

        List<Advertised> filteredAdvertisedList = advertisedList;

        if (cityName != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getAddress().getCity().getName().equals(cityName)
            ).collect(Collectors.toList());
        }

        if (areaMin != null && areaMax != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getArea() >= areaMin && a.getEstateObject().getArea() <= areaMax
            ).collect(Collectors.toList());
        }

        if (floorMin != null && floorMax != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getFloor() >= floorMin && a.getEstateObject().getFloor() <= floorMax
            ).collect(Collectors.toList());
        }

        if (houseFloorMin != null && houseFloorMax != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getMaxFloor() >= houseFloorMin && a.getEstateObject().getMaxFloor() <= houseFloorMax
            ).collect(Collectors.toList());
        }

        if (roomSizeMin != null && roomSizeMax != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getRoomSize() >= roomSizeMin && a.getEstateObject().getRoomSize() <= roomSizeMax
            ).collect(Collectors.toList());
        }

        if (houseTypeName != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getHouseType().getName().equals(houseTypeName)
            ).collect(Collectors.toList());
        }

        if (propertyTypeName != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getPropertyType().getName().equals(propertyTypeName)
            ).collect(Collectors.toList());
        }

        if (renovationTypeName != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getRenovationType().getName().equals(renovationTypeName)
            ).collect(Collectors.toList());
        }

        if (estateObjectTypeName != null) {
            filteredAdvertisedList = filteredAdvertisedList.stream().filter(
                    a -> a.getEstateObject().getEstateObjectType().getName().equals(estateObjectTypeName)
            ).collect(Collectors.toList());
        }

        return filteredAdvertisedList;
    }

    public String checkFilterParam(String param) {
        if (param == null) {
            return null;
        }

        if (param.equals("Не важно") || param.equals("")) {
            return null;
        }

        return param;
    }

    public Integer checkFilterParam(Integer param) {
        if (param == null) {
            return null;
        }

        if (param == 0) {
            return null;
        }

        return param;
    }

    public Float checkFilterParam(Float param) {
        if (param == null) {
            return null;
        }

        if (param == 0) {
            return null;
        }

        return param;
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
