package io.pouch.controller;

import io.pouch.controller.dto.request.UserRequest;
import io.pouch.entities.User;
import io.pouch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequest request) {
        User user = userService.save(request);
        URI location = getHeaderLocation(user.getUserId());
        return ResponseEntity.created(location).build();
    }
}
