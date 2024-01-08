package amadeus.flight.classes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "'user'")
@SuperBuilder
public class User extends BaseEntity {
    @NotNull
    @Column(unique = true)
    private String username;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;
}
