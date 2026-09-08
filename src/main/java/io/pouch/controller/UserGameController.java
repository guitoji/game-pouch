package io.pouch.controller;

import io.pouch.controller.dto.request.UserGameRequest;
import io.pouch.entities.UserGame;
import io.pouch.service.UserGameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/usergames")
public class UserGameController extends GenericController {

    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @PostMapping
    public ResponseEntity<Void> createUserGame(@RequestBody @Valid UserGameRequest request) {
        UserGame userGame = userGameService.save(request);
        URI location = getHeaderLocation(userGame.getUsergameId());
        return ResponseEntity.created(location).build();
    }
}
