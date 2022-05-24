package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.RenovationType;
import com.ineri.ineri_lk.repository.RenovationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RenovationTypeServiceImpl extends AbstractServiceImpl<RenovationType, RenovationTypeRepository> {

    @Autowired
    RenovationTypeRepository renovationTypeRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = renovationTypeRepository;
    }

}
