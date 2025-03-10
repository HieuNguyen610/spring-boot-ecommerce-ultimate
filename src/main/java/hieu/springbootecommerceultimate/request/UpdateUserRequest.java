package hieu.springbootecommerceultimate.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class UpdateUserRequest implements Serializable {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @Email(message = "Email must follow the format")
    private String email;
    @NotBlank(message = "firstName must not be blank")
    private String firstName;
    @NotBlank(message = "lastName must not be blank")
    private String lastName;
    @NotBlank(message = "password must not be blank")
    private String password;
    @NotEmpty(message = "roles must not be empty")
    private List<Long> roles;
    private String photo;
}
