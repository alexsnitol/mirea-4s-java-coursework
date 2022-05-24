package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.AdvertisedStatus;
import com.ineri.ineri_lk.repository.AdvertisedRepository;
import com.ineri.ineri_lk.repository.AdvertisedStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AdvertisedStatusServiceImpl extends AbstractServiceImpl<AdvertisedStatus, AdvertisedStatusRepository> {

    @Autowired
    AdvertisedStatusRepository advertisedStatusRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = advertisedStatusRepository;
    }

}
