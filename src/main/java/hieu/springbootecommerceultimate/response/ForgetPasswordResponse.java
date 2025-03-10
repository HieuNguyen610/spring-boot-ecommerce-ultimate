package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForgetPasswordResponse {
    private String resetToken;
    private String email;
}
