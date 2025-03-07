package hieu.springbootecommerceultimate.response;

import hieu.springbootecommerceultimate.entity.RoleEntity;
import lombok.Builder;
import lombok.Getter;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class UserResponse implements Serializable {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<RoleResponse> roles;
    private boolean enabled;
    private String photo;
}
