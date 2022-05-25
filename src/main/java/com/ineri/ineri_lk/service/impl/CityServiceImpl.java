package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.City;
import com.ineri.ineri_lk.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Service
public class CityServiceImpl extends AbstractServiceImpl<City, CityRepository> {

    @Autowired
    CityRepository cityRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = cityRepository;
    }



}
