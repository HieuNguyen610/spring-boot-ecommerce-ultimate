package hieu.springbootecommerceultimate.service;

import hieu.springbootecommerceultimate.request.CreateRoleRequest;
import hieu.springbootecommerceultimate.response.RoleResponse;
import hieu.springbootecommerceultimate.response.RolePagingResponse;

public interface RoleService {
    RoleResponse addRole(CreateRoleRequest request);

    RoleResponse getRoleById(Long roleId);

    RolePagingResponse getAllRoles();
}
