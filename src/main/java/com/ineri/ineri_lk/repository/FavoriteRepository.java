package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByAdvertisedId(Long id);
    List<Favorite> findAllByUser_Username(String username);

    void deleteByUser_UsernameAndAdvertised_Id(String username, Long id);
}
