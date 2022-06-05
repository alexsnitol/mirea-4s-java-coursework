package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteByUserIdAndFavoriteId(Long userId, Long advertisedId) {
        List<Favorite> favorites = favoriteRepository.findByUser_Id(userId);
        Optional<Favorite> favoriteToDelete = favorites.stream().filter(favorite -> favorite.getAdvertised().getId() == advertisedId).findFirst();
        favoriteToDelete.ifPresent(favorite -> favoriteRepository.deleteById(favorite.getId()));
    }

    public boolean existByUserIdAndAdvertisedId(User user, Advertised advertised) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setAdvertised(advertised);

        return favoriteRepository.exists(Example.of(favorite));
    }
}
