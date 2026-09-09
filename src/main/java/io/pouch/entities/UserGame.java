package io.pouch.entities;

import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_user_games")
@Data
public class UserGame {

/*
    This entity is a correlation of User and Game,
    that was created to abstract the entity game of an instance used by the User on library.
 */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usergame_id")
    private UUID usergameId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false, updatable = false)
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
