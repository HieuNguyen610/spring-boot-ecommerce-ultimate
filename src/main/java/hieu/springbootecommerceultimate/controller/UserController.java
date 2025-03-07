package hieu.springbootecommerceultimate.controller;

import hieu.springbootecommerceultimate.request.CreateUserRequest;
import hieu.springbootecommerceultimate.response.ApiResponse;
import hieu.springbootecommerceultimate.response.UserPagingResponse;
import hieu.springbootecommerceultimate.response.UserResponse;
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


}
