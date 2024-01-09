package amadeus.flight.respositories;

import amadeus.flight.classes.Airport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AirportsRepository extends JpaRepository<Airport,Long> {
    Airport findAirportByCity(String city);

    Airport findAirportById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Airport a SET a.city = :city WHERE a.id = :id")
    void updateAirportById(Long id, String city);
}
