package amadeus.flight.classes;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight extends BaseEntity{
    @NonNull
    private String departureAirport;

    @NonNull
    private String arrivalAirport;

    private int Price;
}
