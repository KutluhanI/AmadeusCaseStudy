package amadeus.flight.DTOs;

import amadeus.flight.classes.Airport;
import amadeus.flight.classes.BaseEntity;
import amadeus.flight.classes.Flight;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class DtoMapper {
    public static FlightDto mapFlightToDto(Flight flight, int ways) {
        final FlightDto flightDto = new FlightDto();
        if(Objects.isNull(flight)) {
            return flightDto;
        }
        flightDto.setId(flight.getId());
        flightDto.setArrivalAirport(flight.getArrivalAirport().getCity());
        flightDto.setDepartureAirport(flight.getDepartureAirport().getCity());
        if(ways == 2) {
            flightDto.setReturnTime(flight.getReturnTime());
        }
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setPrice(flight.getPrice());

        return flightDto;
    }

    public static AirportsDto mapAirportToDto(Airport airport) {
        final AirportsDto airportsDto = new AirportsDto();
        if(Objects.isNull(airport)) {
            return airportsDto;
        }
        airportsDto.setId(airport.getId());
        airportsDto.setCity(airport.getCity());
        airportsDto.setFlightsToArrive(airport.getFlightsToArrive().stream().map(BaseEntity::getId).toList());
        airportsDto.setFlightsToDeparture(airport.getFlightsToDeparture().stream().map(BaseEntity::getId).toList());
        return airportsDto;
    }
}
