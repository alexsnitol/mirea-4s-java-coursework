package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.AdvertisedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisedStatusRepository extends JpaRepository<AdvertisedStatus, Long> {
}
