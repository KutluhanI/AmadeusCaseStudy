package amadeus.flight.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airport extends BaseEntity{
    @NonNull
    private String city;

    public Airport(@NonNull String city) {
        this.city=city;
    }

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> flightsToDeparture;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> flightsToArrive;
}
