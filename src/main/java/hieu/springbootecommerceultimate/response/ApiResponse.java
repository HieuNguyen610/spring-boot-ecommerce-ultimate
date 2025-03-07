package hieu.springbootecommerceultimate.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ApiResponse implements Serializable {

    private String message;
    private Object data;
}
