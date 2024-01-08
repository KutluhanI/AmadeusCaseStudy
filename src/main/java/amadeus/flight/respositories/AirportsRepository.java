package amadeus.flight.respositories;

import amadeus.flight.classes.Airports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportsRepository extends JpaRepository<Airports,Long> {
}
