package amadeus.flight.controllers;

import amadeus.flight.DTOs.AirportsDto;
import amadeus.flight.DTOs.BaseResponse;
import amadeus.flight.DTOs.DtoMapper;
import amadeus.flight.DTOs.FlightDto;
import amadeus.flight.Exceptions.FlightNotFound;
import amadeus.flight.classes.Flight;
import amadeus.flight.respositories.AirportsRepository;
import amadeus.flight.respositories.FlightRepository;
import amadeus.flight.services.AirportsService;
import amadeus.flight.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    private final AirportsService airportsService;

    private final AirportsRepository airportsRepository;

    private final FlightRepository flightRepository;

    @GetMapping("/list")
    public BaseResponse<List<FlightDto>> listFlight(){
        return BaseResponse.<List<FlightDto>>builder().
                payload(flightRepository.findAll().
                        stream().map(flight -> DtoMapper.mapFlightToDto(flight,2)).
                        toList()).build();
    }

    @PostMapping("/create")
    public BaseResponse<FlightDto> createFlight(@RequestParam String departureTime,
                                                  @RequestParam String returnTime) {
        flightService.createNewFlight(new Flight(departureTime,returnTime));
        return BaseResponse.<FlightDto>builder().message("Flight created").build();
    }
    @PostMapping("/delete")
    public BaseResponse<FlightDto> deleteFlight(@RequestParam Long id) {
        try {
            Flight flight = flightService.findFlightById(id);
            flightService.deleteFlight(flight);
        } catch (FlightNotFound flightNotFound) {
            return BaseResponse.<FlightDto>builder().message("Flight not found").build();
        }
        return BaseResponse.<FlightDto>builder().message("Flight is deleted").build();
    }

    @PostMapping("/update")
    public BaseResponse<FlightDto> updateAirportsOfFlight (@RequestParam Long id,
                                                           @RequestParam String depAirport,
                                                           @RequestParam String arrAirport) {
        try {
            flightRepository.updateFlightById(id,
                    airportsRepository.findAirportByCity(depAirport).getId(),
                    airportsRepository.findAirportByCity(arrAirport).getId());
        } catch (Exception e) {
            return BaseResponse.<FlightDto>builder().message(e.getMessage()).build();
        }
        return BaseResponse.<FlightDto>builder().message("Operation succesfull.").build();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void ScheduledFlightCreator() {
        Flight flight = generateMockFlight();
        flightService.createNewFlight(flight);
    }

    private Flight generateMockFlight() {
        return new Flight ("191919","20202020");
    }
}
