package facosta.jwtdemo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@EqualsAndHashCode(exclude = {"users"})
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User> users = new HashSet<>();

    public RoleEntity(@NotNull String name)
    {
        this.name = name;
    }

    public RoleEntity()
    {
    }
}
