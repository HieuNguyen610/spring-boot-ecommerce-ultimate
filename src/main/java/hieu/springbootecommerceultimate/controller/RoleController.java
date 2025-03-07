package hieu.springbootecommerceultimate.controller;

import hieu.springbootecommerceultimate.request.CreateRoleRequest;
import hieu.springbootecommerceultimate.response.ApiResponse;
import hieu.springbootecommerceultimate.response.RoleResponse;
import hieu.springbootecommerceultimate.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRole(@RequestBody CreateRoleRequest request) {
        RoleResponse response = roleService.addRole(request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Add role request")
                        .data(response)
                .build());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse> getRoleById(@PathVariable Long roleId) {
        RoleResponse roleResponse = roleService.getRoleById(roleId);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get role by ID = " + roleId)
                        .data(roleResponse)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAllRoles() {
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get all roles")
                        .data(roleService.getAllRoles())
                .build());
    }
}
