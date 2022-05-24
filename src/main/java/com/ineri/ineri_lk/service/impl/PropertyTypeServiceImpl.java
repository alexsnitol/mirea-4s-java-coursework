package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.PropertyType;
import com.ineri.ineri_lk.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PropertyTypeServiceImpl extends AbstractServiceImpl<PropertyType, PropertyTypeRepository> {

    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = propertyTypeRepository;
    }

}
