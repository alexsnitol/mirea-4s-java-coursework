package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractServiceImpl<M extends AbstractModel, R extends JpaRepository<M, Long>> {
    protected R defaultRepository;

    public List<M> getAll() {
        return defaultRepository.findAll();
    }

    public M getById(Long id) {
        return defaultRepository.findById(id).get();
    }

    public void save(M model) {
        defaultRepository.save(model);
    }

    public void deleteById(Long id) {
        defaultRepository.deleteById(id);
    }

}
