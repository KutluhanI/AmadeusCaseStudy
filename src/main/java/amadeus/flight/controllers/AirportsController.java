package amadeus.flight.controllers;

import amadeus.flight.DTOs.AirportsDto;
import amadeus.flight.DTOs.BaseResponse;
import amadeus.flight.DTOs.DtoMapper;
import amadeus.flight.DTOs.FlightDto;
import amadeus.flight.classes.Airport;
import amadeus.flight.respositories.AirportsRepository;
import amadeus.flight.services.AirportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportsController {

    private final AirportsService airportsService;

    private final AirportsRepository airportsRepository;

    @GetMapping("/list")
    public BaseResponse<List<AirportsDto>> listAirports(){
        return BaseResponse.<List<AirportsDto>>builder().
                payload(airportsRepository.findAll().
                        stream().map(DtoMapper::mapAirportToDto).
                        toList()).build();
    }

    @PostMapping("/create")
    public BaseResponse<AirportsDto> createAirport(@RequestParam String city) {
        airportsService.createAirport(new Airport(city));
        return BaseResponse.<AirportsDto>builder().message("Airport is created").build();
    }
    @PostMapping("/delete")
    public BaseResponse<AirportsDto> deleteAirport(@RequestParam Long id) {
        Airport airport = airportsService.findAirportById(id);
        airportsService.deleteAirport(airport);
        return BaseResponse.<AirportsDto>builder().message("Airport is deleted").build();
    }

    @PostMapping("Update")
    public BaseResponse<AirportsDto> updateAirport(@RequestParam Long id,
                                                   @RequestParam String newCity) {
        if (Objects.isNull(airportsService.findAirportByCity(newCity))) {
            try {
                airportsService.updateAirportCity(airportsService.findAirportById(id), newCity);
            } catch (Exception e) {
                return BaseResponse.<AirportsDto>builder().message(e.getMessage()).build();

            }
            return BaseResponse.<AirportsDto>builder().message("Operation succesfull").build();
        }
        return BaseResponse.<AirportsDto>builder().message("There is already an airport in " + newCity + " city").build();
    }
}
