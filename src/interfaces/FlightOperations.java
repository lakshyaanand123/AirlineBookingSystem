package interfaces;

import model.Flight;
import java.util.List;

public interface FlightOperations {

    boolean addFlight(Flight flight);

    List<Flight> getAllFlights();

    Flight getFlightById(int flightId);

    boolean updateFlight(Flight flight);

    boolean deleteFlight(int flightId);
}