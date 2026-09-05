package io.pouch.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.pouch.entities.enums.GameCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_games")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "game_id", unique = true)
    private Long gameId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private GameCategory category;

    @Column(name = "developer", nullable = false)
    private String developer;

    @Column(name = "release_in")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseIn;

    @Column(name = "price", precision = 13, scale = 2)
    private BigDecimal price;
}
