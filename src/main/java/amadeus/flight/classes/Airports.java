package amadeus.flight.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airports extends BaseEntity{
    @NonNull
    private String city;
}
