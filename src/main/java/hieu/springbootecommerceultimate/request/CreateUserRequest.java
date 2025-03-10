package hieu.springbootecommerceultimate.request;

import lombok.*;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<Long> roles;
    private String photo;

}
