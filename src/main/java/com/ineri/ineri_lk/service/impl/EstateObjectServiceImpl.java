package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.EstateObject;
import com.ineri.ineri_lk.repository.AdvertisedRepository;
import com.ineri.ineri_lk.repository.EstateObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Service
@Scope("singleton")
public class EstateObjectServiceImpl extends AbstractServiceImpl<EstateObject, EstateObjectRepository> {

    @Autowired
    EstateObjectRepository estateObjectRepository;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    AdvertisedRepository advertisedRepository;
    @Autowired
    @Lazy
    AdvertisedServiceImpl advertisedService;

    @PostConstruct
    public void init() {
        defaultRepository = estateObjectRepository;
    }

    @Override
    public void deleteById(Long id) {
        List<Advertised> advertisedList = advertisedRepository.findAllByEstateObjectId(id);
        for (Advertised advertised : advertisedList) {
            advertisedService.deleteById(advertised.getId());
        }
        Long idOfAddress = getById(id).getAddress().getId();
        defaultRepository.deleteById(id);
        addressService.deleteById(idOfAddress);
    }

    public void save(EstateObject estateObject) {
        addressService.save(estateObject.getAddress());
        defaultRepository.save(estateObject);
    }

}
