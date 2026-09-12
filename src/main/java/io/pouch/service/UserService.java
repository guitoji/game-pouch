package io.pouch.service;

import io.pouch.controller.dto.request.UserRequest;
import io.pouch.entities.User;
import io.pouch.repository.RoleRepository;
import io.pouch.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
}
