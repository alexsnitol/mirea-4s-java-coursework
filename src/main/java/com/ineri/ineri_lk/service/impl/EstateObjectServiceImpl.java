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

    @PostConstruct
    public void init() {
        defaultRepository = estateObjectRepository;
    }

}
