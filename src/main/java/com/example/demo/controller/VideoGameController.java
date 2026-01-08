package com.example.demo.controller;

import com.example.demo.model.VideoGame;
import com.example.demo.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videogames")
public class VideoGameController {

    @Autowired
    private VideoGameRepository videoGameRepository;

    @GetMapping
    public List<VideoGame> getAllGames() {
        return videoGameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getGameById(@PathVariable Long id) {
        return videoGameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VideoGame createGame(@RequestBody VideoGame game) {
        return videoGameRepository.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateGame(@PathVariable Long id, @RequestBody VideoGame gameDetails) {
        return videoGameRepository.findById(id).map(game -> {
            game.setName(gameDetails.getName());
            game.setDescription(gameDetails.getDescription());
            game.setImage(gameDetails.getImage());
            game.setReleaseDate(gameDetails.getReleaseDate());
            return ResponseEntity.ok(videoGameRepository.save(game));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (videoGameRepository.existsById(id)) {
            videoGameRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<VideoGame> searchByName(@RequestParam String name) {
        return videoGameRepository.findByNameContainingIgnoreCase(name);
    }
    
    @GetMapping("/search/category")
    public List<VideoGame> searchByCategory(@RequestParam String category) {
        return videoGameRepository.findByCategories_NameIgnoreCase(category);
    }
}