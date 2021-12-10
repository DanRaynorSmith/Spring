package com.qa.springgame.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springgame.domain.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:gameSchema.sql", "classpath:gameData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception{
        Game testGame = new Game("Pathfinder", "1/10/2020", "RPG", 42.99);
        String testGameAsJSON = this.mapper.writeValueAsString(testGame);
        RequestBuilder req = post("/create").content(testGameAsJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isCreated();

        Game testGameCreated = new Game(2,"Pathfinder", "1/10/2020", "RPG", 42.99);
        String testGameCreatedJSON = this.mapper.writeValueAsString(testGameCreated);
        ResultMatcher checkBody = content().json(testGameCreatedJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll () throws Exception{
        Game firstGame = new Game (1,"Battlefield", "1/10/2021", "FPS", 62.99);
        String firstGameAsJSON = this.mapper.writeValueAsString(List.of(firstGame));
        RequestBuilder req = get("/getAll").contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstGameAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
//        this.mvc.perform(get("/getAll"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetId () throws Exception{
        Game firstGame = new Game (1,"Battlefield", "1/10/2021", "FPS", 62.99);
        String firstGameAsJSON = this.mapper.writeValueAsString(firstGame);
        RequestBuilder req = get("/get/" + firstGame.getId()).content(firstGameAsJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstGameAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAllGenre () throws Exception{
        Game firstGame = new Game (1,"Battlefield", "1/10/2021", "FPS", 62.99);
        String firstGameAsJSON = this.mapper.writeValueAsString(List.of(firstGame));
        RequestBuilder req = get("/getAllGenre/" + firstGame.getGenre()).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstGameAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAllPrice () throws Exception{
        Game firstGame = new Game (1,"Battlefield", "1/10/2021", "FPS", 62.99);
        String firstGameAsJSON = this.mapper.writeValueAsString(List.of(firstGame));
        RequestBuilder req = get("/getAllPrice/" + firstGame.getPrice()).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstGameAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testReplace () throws Exception {
        Game firstGame = new Game (1,"Battlefield", "1/10/2021", "FPS", 62.99);

        Game updatedGame = new Game (1,"CoD", "2/2/2021", "FPS", 64.99);
        String updatedGameAsJSON = this.mapper.writeValueAsString(updatedGame);

        RequestBuilder req = put("/replace/" + firstGame.getId())
                .content(updatedGameAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(updatedGameAsJSON);
        ResultMatcher checkStatus = status().isAccepted();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testRemove () throws Exception {
        this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
    }
}
