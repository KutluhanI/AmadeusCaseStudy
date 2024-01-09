package amadeus.flight.services;

import amadeus.flight.classes.Airport;
import amadeus.flight.respositories.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AirportsService implements AirportsServiceInt {
    private final AirportsRepository airportsRepository;


    @Override
    public Airport findAirportById(Long id) {
        return airportsRepository.findAirportById(id);
    }

    @Override
    public Airport findAirportByCity(String city) {
        return airportsRepository.findAirportByCity(city);
    }

    @Override
    public Airport createAirport(Airport newAirport) {
        Objects.requireNonNull(newAirport,"Airport is null");
        airportsRepository.save(newAirport);
        return newAirport;
    }

    @Override
    public void deleteAirport(Airport airport) {
        Objects.requireNonNull(airport,"Airport is null");
        airportsRepository.delete(airport);
    }

    @Override
    public void updateAirportCity(Airport airport, String newCity) {
        Objects.requireNonNull(airport,"Airport is null");
        airportsRepository.updateAirportById(airport.getId(),newCity);
    }
}
