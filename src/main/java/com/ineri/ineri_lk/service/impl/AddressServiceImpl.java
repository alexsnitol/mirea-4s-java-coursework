package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Address;
import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.EstateObject;
import com.ineri.ineri_lk.model.Form;
import com.ineri.ineri_lk.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AddressServiceImpl extends AbstractServiceImpl<Address, AddressRepository> {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    @Lazy
    EstateObjectServiceImpl estateObjectService;
    @Autowired
    @Lazy
    FormService formService;

    @PostConstruct
    public void init() {
        defaultRepository = addressRepository;
    }

    public void deleteById(Long id) {

        List<EstateObject> estateObjectList = estateObjectService.getAllByAddressId(id);
        List<Form> formList = formService.getAllByAddressId(id);

        if (!estateObjectList.isEmpty()) {
            for (EstateObject estateObject : estateObjectList) {
                estateObjectService.deleteById(estateObject.getId());
            }
        }

        if (!formList.isEmpty()) {
            for (Form form : formList) {
                formService.deleteById(form.getId());
            }
        }

        defaultRepository.deleteById(id);
    }

}
