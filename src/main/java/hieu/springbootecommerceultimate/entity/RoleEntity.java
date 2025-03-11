package hieu.springbootecommerceultimate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity(name = "roles")
public class RoleEntity extends AbstractEntity{

    private String name;
    private String description;

    @ManyToMany(mappedBy = "roles")  // Must match field name in UserEntity
    private List<UserEntity> users = new ArrayList<>();
}
