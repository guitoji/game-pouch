package io.pouch.controller;

import io.pouch.controller.dto.request.UserGameRequest;
import io.pouch.controller.dto.response.UserGameResponse;
import io.pouch.controller.dto.update.UserGameUpdate;
import io.pouch.entities.UserGame;
import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;
import io.pouch.service.UserGameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<UserGameResponse>> searchUserGame(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "status", required = false) Status status,
            @RequestParam(name = "rating", required = false) Rating rating,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(userGameService.search(title, status, rating, page, pageSize));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserGameResponse> updateUserGame(@PathVariable String id, @RequestBody UserGameUpdate update) {
        return ResponseEntity.ok(userGameService.update(id, update));
    }
}
