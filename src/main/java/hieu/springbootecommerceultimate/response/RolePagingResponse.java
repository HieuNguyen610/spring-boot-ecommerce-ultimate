package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class RolePagingResponse implements Serializable {
    private int count;
    private int page;
    private int size;
    private List<RoleResponse> data;
}
