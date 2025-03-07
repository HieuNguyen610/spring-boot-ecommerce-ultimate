package hieu.springbootecommerceultimate.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class CreateRoleRequest implements Serializable {
    private String name;
    private String description;
}
