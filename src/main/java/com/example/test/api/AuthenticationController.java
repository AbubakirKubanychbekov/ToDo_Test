package com.example.test.api;

import com.example.test.dto.response.AuthenticationSignInResponse;
import com.example.test.dto.response.AuthenticationSignUpResponse;
import com.example.test.dto.request.SignInRequest;
import com.example.test.dto.request.SignUpRequest;
import com.example.test.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API")
@RequiredArgsConstructor
@PermitAll
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    @Operation(summary = "Зарегистрироваться", description = "Регистрация  аккаунта")
    public AuthenticationSignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    @Operation(summary = "Вход в свой аккаунт")
    public AuthenticationSignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }
}
