package com.willowridge.videogame;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    //returns all games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return ResponseEntity.ok(games);
    }
    @PostMapping(produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> add(@RequestBody Game game) {
        Game newGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PutMapping("/{gameId}")
    //only usable if user is an admin
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> updateGame(@PathVariable String gameId, @RequestBody Game updatedGame) {
        Optional<Game> existingGame = gameRepository.findById(gameId);

        if (existingGame.isPresent()) {
            // Set the existing ID to avoid creating a new one
            updatedGame.setId(gameId);

            Game savedGame = gameRepository.save(updatedGame);
            return ResponseEntity.ok(savedGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        Optional<Game> existingGame = this.gameRepository.findById(id);
        if(existingGame.isPresent()){
            this.gameRepository.delete(existingGame.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //returns one game based on what id
    @GetMapping("/{id}")

    //path variable gets the id from the url
    //response entity is saying there could be errors
    public ResponseEntity<Game> getGameById(@PathVariable String id)
    {
        //optional means you have something that might not exist
        return gameRepository.findById(id)
                //if a game is found then return HTTP ok
                .map(ResponseEntity::ok)
                //if not then return a 404
                .orElse(ResponseEntity.notFound().build());
    }
}
