package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByAdvertisedId(Long id);
    void deleteByAdvertisedId(Long id);

}
