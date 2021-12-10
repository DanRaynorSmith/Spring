package com.qa.springgame.service;

import com.qa.springgame.domain.Game;
import com.qa.springgame.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class GameService {

    private GameRepo repo;

    @Autowired
    public GameService (GameRepo repo) {
        this.repo = repo;
    }

    public Game createGame(Game game) {
        Game created = this.repo.save(game);
        return created;
    }

    public List<Game> getAllGames(){
        return this.repo.findAll();
    }

    public Game getGame(Integer id) {
        return this.repo.findById(id).get();
    }

    public List<Game> findAllByGenres(String genre) {
        return this.repo.findAllByGenreIgnoreCase(genre);
    }

    public List<Game> findAllByPrice(Double price) {
        return this.repo.findAllByPrice(price);
    }

    public Game replaceGame(Integer id, Game newGame) {
        Game replaced = this.repo.findById(id).get();

        replaced.setGenre(newGame.getGenre());
        replaced.setTitle(newGame.getTitle());
        replaced.setReleaseDate(newGame.getReleaseDate());
        replaced.setPrice(newGame.getPrice());

        Game updated = this.repo.save(replaced);
        return updated;
    }

    public void removeGame(Integer id) {
        this.repo.deleteById(id);
    }

}
