package amadeus.flight.DTOs;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportsDto {

    private Long id;

    private String city;

    private List<Long> flightsToDeparture;

    private List<Long> flightsToArrive;

}
