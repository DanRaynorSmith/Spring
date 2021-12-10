package com.qa.springgame.repo;

import com.qa.springgame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {

    List<Game> findAllByGenreIgnoreCase(String genre);
    List<Game> findAllByPrice(Double price);

}
