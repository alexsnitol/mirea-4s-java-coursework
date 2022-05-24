package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.EstateObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateObjectRepository extends JpaRepository<EstateObject, Long> {
}
