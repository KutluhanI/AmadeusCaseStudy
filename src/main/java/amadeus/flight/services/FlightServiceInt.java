package amadeus.flight.services;

import amadeus.flight.classes.Flight;

import java.util.List;

public interface FlightServiceInt {
    Flight createNewFlight(Flight newFlight);

    void deleteFlight (Flight flight);

    void updateFlightDepTime(Flight flight, String newDepTime);

    void updateFlightReturnTime(Flight flight, String newArrTime);

    Flight findFlightById(Long id);

    Flight findFlightByDepartureTime(String depTime);

    List<Flight> findOneWayFlights(String departure, String arrival, String departureDate);

    List<Flight> findTwoWayFlights(String departure, String arrival, String departureDate, String returnDate);

}
