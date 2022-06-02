package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FavoriteServiceImpl extends AbstractServiceImpl<Favorite, FavoriteRepository> {

    @Autowired
    FavoriteRepository favoriteRepository;

    @PostConstruct
    public void init() {
        defaultRepository = favoriteRepository;
    }

    public List<Favorite> getAllByAdvertisedId(Long id) {
        return favoriteRepository.findAllByAdvertisedId(id);
    }

    public List<Favorite> getUserFavorites(String username) {
        return favoriteRepository.findAllByUser_Username(username);
    }

    public void deleteByUsernameAndFavoriteId(String username, Long id) {
        favoriteRepository.deleteByUser_UsernameAndAdvertised_Id(username, id);
    }

}
