package io.pouch.service;

import io.pouch.controller.dto.request.UserRequest;
import io.pouch.entities.Role;
import io.pouch.entities.User;
import io.pouch.repository.RoleRepository;
import io.pouch.repository.UserRepository;
import io.pouch.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public User save(UserRequest request) {
        User user = userMapper.toEntity(request);
        var defaultRole = roleRepository.findByName(Role.Values.USER.name());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Set.of(defaultRole));

        return userRepository.save(user);
    }
}
