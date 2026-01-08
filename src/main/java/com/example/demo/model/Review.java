package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorName;
    private int rating;
    private String comment;
    @Temporal(TemporalType.DATE)
    private Date date = new Date(); 
    @ManyToOne
    @JoinColumn(name = "video_game_id")
    @JsonIgnore 
    private VideoGame videoGame;
}