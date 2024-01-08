package amadeus.flight.DTOs;

import jakarta.persistence.Temporal;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Setter
@Getter
@Data
@AllArgsConstructor
public class FlightDto {
    private String departureAirport;

    private String arrivalAirport;

    private Date arrivalTime;

    private Date departureTime;

    private int Price;
}
