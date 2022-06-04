package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByAdvertisedId(Long id);
    List<Favorite> findAllByUser_Username(String username);

    List<Favorite> findByUser_Id(Long userId);
    void deleteFavoriteByAdvertised_Id(Long id);
}
