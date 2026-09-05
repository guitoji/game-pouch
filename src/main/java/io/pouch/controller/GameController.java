package io.pouch.controller;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/games")
public class GameController extends GeneralController{

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public ResponseEntity<Void> createGame(@RequestBody @Valid GameRequest request) {
        UUID id = gameService.save(request).getGameId();
        URI location = getHeaderLocation(id);
        return ResponseEntity.created(location).build();
    }
}
