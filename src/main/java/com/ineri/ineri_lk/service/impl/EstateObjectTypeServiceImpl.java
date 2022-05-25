package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.EstateObjectType;
import com.ineri.ineri_lk.repository.EstateObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Service
public class EstateObjectTypeServiceImpl extends AbstractServiceImpl<EstateObjectType, EstateObjectTypeRepository> {

    @Autowired
    EstateObjectTypeRepository estateObjectTypeRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = estateObjectTypeRepository;
    }

}
