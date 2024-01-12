package com.example.test.dto.response;

import com.example.test.enums.Role;

public record AuthenticationSignUpResponse(
        Long id,
        String fullName,
        String token,
        String email,
        Role role
) {
}
