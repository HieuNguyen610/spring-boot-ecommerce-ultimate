package hieu.springbootecommerceultimate.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UpdateUserRequest {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<Long> roles;
    private String photo;
}
