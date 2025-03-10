package hieu.springbootecommerceultimate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.springbootecommerceultimate.entity.RoleEntity;
import hieu.springbootecommerceultimate.repository.RoleRepository;
import hieu.springbootecommerceultimate.request.CreateRoleRequest;
import hieu.springbootecommerceultimate.response.RolePagingResponse;
import hieu.springbootecommerceultimate.response.RoleResponse;
import hieu.springbootecommerceultimate.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import hieu.springbootecommerceultimate.exception.RoleNotExistException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ROLE-SERVICE")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse addRole(CreateRoleRequest request) {
        RoleEntity roleEntity = roleRepository.save(convertRequestToEntity(request));
        return convertEntityToResponse(roleEntity);
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotExistException("Role not found"));
        return convertEntityToResponse(roleEntity);
    }

    @Override
    public RolePagingResponse getAllRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleResponse> data = roleEntities.stream().map(this::convertEntityToResponse).toList();

        return RolePagingResponse.builder()
                .count(roleEntities.size())
                .data(data)
                .build();
    }

    private RoleResponse convertEntityToResponse(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        return RoleResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .build();
    }

    private RoleEntity convertRequestToEntity(CreateRoleRequest request) {
        if (request == null) {
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(request.getName());
        roleEntity.setDescription(request.getDescription());
        return roleEntity;
    }
}
