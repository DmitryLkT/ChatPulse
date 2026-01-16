package org.lukdt.user_service.service.interface_service;

import org.lukdt.user_service.dto.LoginRequest;
import org.lukdt.user_service.dto.RegisterRequest;

public interface AuthService {
  String register(RegisterRequest request);

  String login(LoginRequest request);
}
