package io.pouch.controller;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.controller.dto.response.GameResponse;
import io.pouch.controller.dto.update.GameUpdate;
import io.pouch.entities.enums.Category;
import io.pouch.entities.enums.Rating;
import io.pouch.service.GameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/games")
public class GameController extends GeneralController{

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody @Valid GameRequest request) {
        UUID id = gameService.save(request).getGameId();
        URI location = getHeaderLocation(id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<GameResponse>> searchGame(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "developer", required = false) String developer,
            @RequestParam(name = "publisher", required = false) String publisher,
            @RequestParam(name = "rating", required = false) Rating rating,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity
                .ok(gameService.search(title, developer, publisher, rating, page, pageSize));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable String id, @RequestBody @Valid GameUpdate update) {
        return ResponseEntity.ok(gameService.update(id, update));
    }
}
