package com.example.demo.repository;

import com.example.demo.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findByNameContainingIgnoreCase(String name);
    List<VideoGame> findByCategories_NameIgnoreCase(String categoryName);
}