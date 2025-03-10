package hieu.springbootecommerceultimate.service;

import hieu.springbootecommerceultimate.request.CreateUserRequest;
import hieu.springbootecommerceultimate.response.ForgetPasswordResponse;
import hieu.springbootecommerceultimate.response.ResetPasswordResponse;
import hieu.springbootecommerceultimate.response.UserPagingResponse;
import hieu.springbootecommerceultimate.response.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long userId);

    UserPagingResponse getUsers(String email, int page, int size);

    UserResponse activeUser(Long userId);

    ForgetPasswordResponse forgetPassword(String email);

    ResetPasswordResponse resetPassword(String token);

    UserResponse updateUser(UserUpdateRequest request);
}
