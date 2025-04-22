package com.willowridge.videogame;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("games")
public class Game {


    //defining attributes of a game
    @Id
    private String id;

    private String title;
    private String genre;
    private double price;
    private String developer;
    private String imageUrl;




    //game constructor
    public Game(String id, String title, String genre, double price, String developer, String imageUrl) {


        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.developer = developer;
        this.imageUrl = imageUrl;

    }
    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
