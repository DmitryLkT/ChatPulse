package org.lukdt.user_service.service;

import lombok.RequiredArgsConstructor;

import org.lukdt.user_service.dto.LoginRequest;
import org.lukdt.user_service.dto.RegisterRequest;
import org.lukdt.user_service.entity.Role;
import org.lukdt.user_service.entity.User;
import org.lukdt.user_service.exception.custom_exception.InvalidCredentialsException;
import org.lukdt.user_service.exception.custom_exception.UserAlreadyExistsException;
import org.lukdt.user_service.exception.custom_exception.UserNotFoundException;
import org.lukdt.user_service.repository.UserRepository;
import org.lukdt.user_service.security.JwtService;
import org.lukdt.user_service.service.interface_service.AuthService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public String register(RegisterRequest request) {
    if(userRepository.existsByEmail(request.getEmail())) {
        throw new UserAlreadyExistsException(String.format(request.getEmail()));
    }

    User user = User.builder()
              .fullName(request.getFullName())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(Role.USER)
              .build();

    userRepository.save(user);

    return jwtService.generateToken(user.getEmail());
  }

  @Override
  public String login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserNotFoundException(request.getEmail()));
    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new InvalidCredentialsException();
    }

    return jwtService.generateToken(user.getEmail());
  }
}
