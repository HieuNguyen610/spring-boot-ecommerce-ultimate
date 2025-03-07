package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class RoleResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
}
