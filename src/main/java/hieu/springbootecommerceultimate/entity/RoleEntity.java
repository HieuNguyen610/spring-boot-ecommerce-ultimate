package hieu.springbootecommerceultimate.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity(name = "roles")
public class RoleEntity extends AbstractEntity{

    private String name;
    private String description;
}
