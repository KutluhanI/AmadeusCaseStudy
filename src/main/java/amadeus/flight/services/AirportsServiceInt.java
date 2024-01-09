package amadeus.flight.services;

import amadeus.flight.classes.Airport;

public interface AirportsServiceInt {

    Airport findAirportById(Long id);

    Airport findAirportByCity(String city);
    Airport createAirport (Airport newAirport);

    void deleteAirport (Airport airport);

    void updateAirportCity (Airport airport, String newCity);
}
