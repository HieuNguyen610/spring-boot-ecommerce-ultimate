package hieu.springbootecommerceultimate.service;

import hieu.springbootecommerceultimate.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginDto);
}
