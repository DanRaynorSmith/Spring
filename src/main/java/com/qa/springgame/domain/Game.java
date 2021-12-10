package com.qa.springgame.domain;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String releaseDate;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Double price;

    public Game() {}

    public Game(Integer id) {
        this.id = id;
    }

    public Game(String title, String releaseDate, String genre, double price) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.price = price;
    }

    public Game(Integer id, String title, String releaseDate, String genre, Double price) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game: Title = " + title + ", Release Date = " + releaseDate + ", Genre = " + genre + ", Price = " + price;
    }

}
