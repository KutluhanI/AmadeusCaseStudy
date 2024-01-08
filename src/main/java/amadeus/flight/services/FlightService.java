package amadeus.flight.services;

import amadeus.flight.respositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
}
