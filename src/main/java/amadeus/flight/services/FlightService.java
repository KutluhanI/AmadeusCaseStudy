package amadeus.flight.services;

import amadeus.flight.Exceptions.FlightNotFound;
import amadeus.flight.classes.Airport;
import amadeus.flight.classes.Flight;
import amadeus.flight.respositories.AirportsRepository;
import amadeus.flight.respositories.FlightRepository;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightService implements FlightServiceInt {

    private final FlightRepository flightRepository;

    private final AirportsRepository airportsRepository;

    @Override
    public Flight createNewFlight(Flight newFlight) {
        Objects.requireNonNull(newFlight, "The flight object is null");
        flightRepository.save(newFlight);
        return newFlight;
    }

    @Override
    public void deleteFlight(Flight flight) {
        Objects.requireNonNull(flight,"Object is already null");
        flightRepository.delete(flight);
    }

    @Override
    public void updateFlightDepTime(Flight flight, String newDepTime) {
        Objects.requireNonNull(flight,"flight is null");
        flightRepository.updateFlightDepartureTime(flight.getId(), newDepTime);
        flight.setDepartureTime(newDepTime);
    }

    @Override
    public void updateFlightReturnTime(Flight flight, String newReturnTime) {
        Objects.requireNonNull(flight,"flight is null");
        flightRepository.updateFlightReturnTime(flight.getId(), newReturnTime);
        flight.setReturnTime(newReturnTime);
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findFlightById(id).orElseThrow(FlightNotFound::new);
    }

    @Override
    public Flight findFlightByDepartureTime(String depTime) {
        return flightRepository.findFlightByDepartureTime(depTime).orElseThrow(FlightNotFound::new);
    }

    public List<Flight> findOneWayFlights(String departure, String arrival, String departureDate) {
        List<Flight> oneWayFlights = new ArrayList<>();

        for (Flight flight : flightRepository.findAll()) {
            try {
                if (flight.getDepartureAirport().equals(airportsRepository.findAirportByCity(departure))
                        && flight.getArrivalAirport().equals(airportsRepository.findAirportByCity(arrival))
                        && flight.getDepartureTime().equals(departureDate)) {
                    oneWayFlights.add(flight);
                }
            } catch (Exception e) {

            }
        }
        return oneWayFlights;
    }

    public List<Flight> findTwoWayFlights(String departure, String arrival, String departureDate, String returnDate) {
        List<Flight> TwoWayFlights = new ArrayList<>();

        for (Flight flight : flightRepository.findAll()) {
            try {
                if (flight.getDepartureAirport().equals(airportsRepository.findAirportByCity(departure))
                        && flight.getArrivalAirport().equals(airportsRepository.findAirportByCity(arrival))
                        && flight.getDepartureTime().equals(departureDate)
                        && flight.getReturnTime().equals(returnDate)) {
                    TwoWayFlights.add(flight);
                }
            }
            catch (Exception e){}
        }

        return TwoWayFlights;
    }
}
