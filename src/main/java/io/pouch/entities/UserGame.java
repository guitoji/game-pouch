package io.pouch.entities;

import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;
import jakarta.persistence.*;

@Entity
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PURCHASED;

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating = Rating.UNRATED;

    @Column(name = "hours_played")
    private Double hoursPlayed = 0.0;

    @Column(name = "review")
    private String review;
}
