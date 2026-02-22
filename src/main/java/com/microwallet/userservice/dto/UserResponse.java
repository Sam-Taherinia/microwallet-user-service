package com.microwallet.userservice.dto;

public record UserResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        String status
) {}
