package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Address;
import com.ineri.ineri_lk.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AddressServiceImpl extends AbstractServiceImpl<Address, AddressRepository> {

    @Autowired
    AddressRepository addressRepository;

    @PostConstruct
    public void init() {
        defaultRepository = addressRepository;
    }

}
