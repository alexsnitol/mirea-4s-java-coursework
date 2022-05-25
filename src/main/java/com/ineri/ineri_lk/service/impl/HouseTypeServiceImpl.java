package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.HouseType;
import com.ineri.ineri_lk.model.PropertyType;
import com.ineri.ineri_lk.repository.HouseTypeRepository;
import com.ineri.ineri_lk.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Service
public class HouseTypeServiceImpl extends AbstractServiceImpl<HouseType, HouseTypeRepository> {

    @Autowired
    HouseTypeRepository houseTypeRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = houseTypeRepository;
    }

}
