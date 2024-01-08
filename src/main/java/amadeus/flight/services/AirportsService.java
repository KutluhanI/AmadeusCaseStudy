package amadeus.flight.services;

import amadeus.flight.respositories.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportsService {
    private final AirportsRepository airportsRepository;


}
