package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.AdvertisedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisedPhotoRepository extends JpaRepository<AdvertisedPhoto, Long> {

    List<AdvertisedPhoto> findAllByAdvertisedId(Long id);
    List<AdvertisedPhoto> findAllByFormId(Long id);

}
