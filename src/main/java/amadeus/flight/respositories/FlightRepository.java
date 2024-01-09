package amadeus.flight.respositories;

import amadeus.flight.classes.Airport;
import amadeus.flight.classes.Flight;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findFlightByArrivalAirport(Airport airport);

    Optional<Flight> findFlightByDepartureTime(String depTime);

    Optional<Flight> findFlightById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f SET f.arrivalAirport.id = :arrivalAirportID," +
            " f.departureAirport.id = :departureAirportID WHERE f.id = :id")
    void updateFlightById(Long id, Long departureAirportID, Long arrivalAirportID);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f SET f.returnTime = :newReturnTime WHERE f.id = :id")
    void updateFlightReturnTime(@Param("id") Long id, @Param("newReturnTime") String newReturnTime);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f SET f.departureTime = :newDepartureTime WHERE f.id = :id")
    void updateFlightDepartureTime(@Param("id") Long id, @Param("newDepartureTime") String newReturnTime);
}
