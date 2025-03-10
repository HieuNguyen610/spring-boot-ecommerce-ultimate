package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResetPasswordResponse {
    private String email;
    private String password;
}
