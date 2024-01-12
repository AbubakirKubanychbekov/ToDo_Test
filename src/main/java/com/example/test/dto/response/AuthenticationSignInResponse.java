package com.example.test.dto.response;

import com.example.test.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationSignInResponse(
        Long id,
        String fullName,
        String token,
        String email,
        Role role
) {
}
