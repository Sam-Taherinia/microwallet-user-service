package com.microwallet.userservice.service;

import com.microwallet.userservice.domain.User;
import com.microwallet.userservice.domain.UserStatus;
import com.microwallet.userservice.dto.CreateUserRequest;
import com.microwallet.userservice.dto.UserResponse;
import com.microwallet.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUserRequest request){

        if (userRepository.existsByEmail(request.email()))
                throw new RuntimeException("Email already exists");

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .password(hashedPassword)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .createdAt(Instant.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId().toString(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getStatus().name());

    }
}
