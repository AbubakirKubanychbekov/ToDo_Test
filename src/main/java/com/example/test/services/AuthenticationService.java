package com.example.test.services;

import com.example.test.dto.response.AuthenticationSignInResponse;
import com.example.test.dto.response.AuthenticationSignUpResponse;
import com.example.test.dto.request.SignInRequest;
import com.example.test.dto.request.SignUpRequest;

public interface AuthenticationService {
    AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest);

    AuthenticationSignInResponse signIn(SignInRequest signInRequest);
}
