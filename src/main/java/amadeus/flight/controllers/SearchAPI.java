package amadeus.flight.controllers;

import amadeus.flight.DTOs.BaseResponse;
import amadeus.flight.DTOs.DtoMapper;
import amadeus.flight.DTOs.FlightDto;
import amadeus.flight.Exceptions.FlightNotFound;
import amadeus.flight.services.FlightServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchAPI {

    private final FlightServiceInt flightService;
    @GetMapping("/flights")
    public BaseResponse<List<FlightDto>> listFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate
    ) {
        try {
            if (returnDate == null && Objects.nonNull(flightService.findFlightByDepartureTime(departureDate))) {
                return BaseResponse.<List<FlightDto>>builder().payload(flightService.findOneWayFlights(departure, arrival, departureDate)
                        .stream().map(flight -> DtoMapper.mapFlightToDto(flight,1)).toList()).message("One way flights:").build();
            } else if (Objects.nonNull(flightService.findFlightByDepartureTime(departureDate))){
                return BaseResponse.<List<FlightDto>>builder().payload(flightService.findTwoWayFlights(departure, arrival, departureDate, returnDate)
                        .stream().map(flight -> DtoMapper.mapFlightToDto(flight,2)).toList()).message("Two way flights: ").build();
            } else {
                return BaseResponse.<List<FlightDto>>builder().message("No flights found").build();
            }
        } catch (FlightNotFound flightNotFound) {
            return BaseResponse.<List<FlightDto>>builder().message("No flights found on given departure time").build();
        }
    }
}
