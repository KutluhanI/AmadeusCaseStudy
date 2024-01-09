package amadeus.flight.DTOs;

import jakarta.persistence.Temporal;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private String departureAirport;

    private String arrivalAirport;

    private String returnTime;

    private String departureTime;

    private int Price;
}
