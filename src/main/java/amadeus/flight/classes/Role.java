package amadeus.flight.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Role extends BaseEntity{
    @Column(unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
