package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.EstateObject;
import com.ineri.ineri_lk.repository.EstateObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EstateObjectServiceImpl extends AbstractServiceImpl<EstateObject, EstateObjectRepository> {

    @Autowired
    EstateObjectRepository estateObjectRepository;
    @Autowired
    AddressServiceImpl addressService;

    @PostConstruct
    public void init() {
        defaultRepository = estateObjectRepository;
    }

    @Override
    public void deleteById(Long id) {
        Long idOfAddress = getById(id).getAddress().getId();
        defaultRepository.deleteById(id);
        addressService.deleteById(idOfAddress);
    }

    public void save(EstateObject estateObject) {
        addressService.save(estateObject.getAddress());
        defaultRepository.save(estateObject);
    }

}
