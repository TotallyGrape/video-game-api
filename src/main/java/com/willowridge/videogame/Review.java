

package com.willowridge.videogame;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reviews")
public class Review {

    @Id
    private String id;
    private String gameId;
    private String username;
    private double rating;
    private String comment;


    public Review() {}

    //Full constructor
    public Review(String id, String gameId, String username, int rating, String comment) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    //Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
