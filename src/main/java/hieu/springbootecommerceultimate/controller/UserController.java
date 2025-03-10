package hieu.springbootecommerceultimate.controller;

import hieu.springbootecommerceultimate.request.CreateUserRequest;
import hieu.springbootecommerceultimate.response.*;
import hieu.springbootecommerceultimate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Create user")
                        .data(response)
                .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> addUser(@PathVariable("userId") Long userId) {
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Get user id = " + userId)
                .data(response)
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchUser(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        UserPagingResponse response = userService.getUsers(email, page, size);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Search user")
                        .data(response)
                .build());
    }

    @PostMapping("/active")
    public ResponseEntity<ApiResponse> activeUser(@RequestParam Long userId) {
        UserResponse response = userService.activeUser(userId);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Active user id = " + userId)
                        .data(response)
                .build());
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse> forgetPassword(@RequestParam String email) {
        ForgetPasswordResponse response = userService.forgetPassword(email);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Forget password")
                        .data(response)
                .build());
    }

    @GetMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam String token) {
        ResetPasswordResponse response = userService.resetPassword(token);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Reset password")
                        .data(response)
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request) {
        UserResponse response = userService.updateUser(request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Update user")
                        .data(response)
                .build());
    }

}
