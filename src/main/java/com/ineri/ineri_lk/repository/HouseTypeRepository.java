package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseTypeRepository extends JpaRepository<HouseType, Long> {
}
