package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Form;
import com.ineri.ineri_lk.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public void save(Form form) {
        formRepository.save(form);
    }

    public List<Form> getAll() {
        return formRepository.findAll();
    }

    public Form getById(Long id) {
        return formRepository.getById(id);
    }

    public void deleteById(Long id) {
        formRepository.deleteById(id);
    }


}
