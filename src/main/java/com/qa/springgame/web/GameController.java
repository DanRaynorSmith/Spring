package com.qa.springgame.web;

import java.util.List;

import com.qa.springgame.domain.Game;
import com.qa.springgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

class GameController {

    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/create") //201
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game created = this.service.createGame(game);
        ResponseEntity<Game> response = new ResponseEntity<Game>(created, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/getAll") //200
    public ResponseEntity<List<Game>> getAllGames(){
        return ResponseEntity.ok(this.service.getAllGames());
    }

    @GetMapping("/get/{id}") //200
    public Game getGame(@PathVariable Integer id) {
        return this.service.getGame(id);
    }

    @GetMapping("/getAllGenre/{genre}")
    public ResponseEntity<List<Game>> findAllByGenre(@PathVariable String genre) {
        List<Game> found = this.service.findAllByGenres(genre);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/getAllPrice/{price}")
    public ResponseEntity<List<Game>> findAllByPrice(@PathVariable Double price) {
        List<Game> found = this.service.findAllByPrice(price);
        return ResponseEntity.ok(found);
    }

    @PutMapping("/replace/{id}") //202
    public ResponseEntity<Game> replaceGame(@PathVariable Integer id, @RequestBody Game newGame) {
        Game body = this.service.replaceGame(id, newGame);
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/remove/{id}") //204
    public ResponseEntity<?> removeGame(@PathVariable Integer id) {
        this.service.removeGame(id);
        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
    }
}
