package com.example.test.services.impl;

import com.example.test.config.JwtService;
import com.example.test.dto.response.AuthenticationSignInResponse;
import com.example.test.dto.response.AuthenticationSignUpResponse;
import com.example.test.dto.request.SignInRequest;
import com.example.test.dto.request.SignUpRequest;
import com.example.test.entities.User;
import com.example.test.enums.Role;
import com.example.test.exceptions.AlreadyExistException;
import com.example.test.exceptions.BadCredentialException;
import com.example.test.exceptions.NotFoundException;
import com.example.test.repositories.UserRepository;
import com.example.test.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Пользователь с адресом электронной почты:"
                    + signUpRequest.getEmail() + " уже существует");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        log.info("Пользователь успешно сохранен с идентификатором:" + user.getId());
        String token = jwtService.generateToken(user);
        return new AuthenticationSignUpResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole());
    }

    @Override
    public AuthenticationSignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException(String.format("Пользователь с адресом электронной почты: %s не найден!", signInRequest.getEmail())));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.info("Недействительный пароль");
            throw new BadCredentialException("Недействительный пароль");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));
        String token = jwtService.generateToken(user);
        return new AuthenticationSignInResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole());
    }
}
