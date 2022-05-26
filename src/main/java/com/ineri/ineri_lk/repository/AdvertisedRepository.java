package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.EstateObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Repository
public interface AdvertisedRepository extends JpaRepository<Advertised, Long> {
    List<Advertised> findAllByEstateObjectId(Long id);
}
