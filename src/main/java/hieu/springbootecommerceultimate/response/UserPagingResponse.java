package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class UserPagingResponse implements Serializable {

    private int count;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<UserResponse> users;
}
