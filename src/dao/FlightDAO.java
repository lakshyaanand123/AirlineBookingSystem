package dao;

import database.DBConnection;
import interfaces.FlightOperations;
import model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO implements FlightOperations {

    private Connection connection;

    public FlightDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addFlight(Flight flight) {

        String query = "INSERT INTO flights(flight_name, source, destination, departure_time, arrival_time, travel_date, ticket_price, total_seats, available_seats) VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, flight.getFlightName());
            ps.setString(2, flight.getSource());
            ps.setString(3, flight.getDestination());
            ps.setString(4, flight.getDepartureTime());
            ps.setString(5, flight.getArrivalTime());
            ps.setDate(6, Date.valueOf(flight.getTravelDate()));
            ps.setDouble(7, flight.getPrice());
            ps.setInt(8, flight.getTotalSeats());
            ps.setInt(9, flight.getAvailableSeats());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Flight getFlightById(int flightId) {

        String query = "SELECT * FROM flights WHERE flight_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, flightId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("travel_date"),
                        rs.getDouble("ticket_price"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Flight> getAllFlights() {

        List<Flight> flights = new ArrayList<>();

        String query = "SELECT * FROM flights";

        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                flights.add(new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("travel_date"),
                        rs.getDouble("ticket_price"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public boolean updateFlight(Flight flight) {

        String query = "UPDATE flights SET flight_name=?, source=?, destination=?, departure_time=?, arrival_time=?, travel_date=?, ticket_price=?, total_seats=?, available_seats=? WHERE flight_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, flight.getFlightName());
            ps.setString(2, flight.getSource());
            ps.setString(3, flight.getDestination());
            ps.setString(4, flight.getDepartureTime());
            ps.setString(5, flight.getArrivalTime());
            ps.setDate(6, Date.valueOf(flight.getTravelDate()));
            ps.setDouble(7, flight.getPrice());
            ps.setInt(8, flight.getTotalSeats());
            ps.setInt(9, flight.getAvailableSeats());
            ps.setInt(10, flight.getFlightId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteFlight(int flightId) {

        String query = "DELETE FROM flights WHERE flight_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, flightId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void displayAllFlights() {

        List<Flight> flights = getAllFlights();

        if (flights.isEmpty()) {

            System.out.println("\nNo Flights Available.");
            return;
        }

        System.out.println("\n================ AVAILABLE FLIGHTS ================");

        for (Flight flight : flights) {

            System.out.println("----------------------------------------------");
            System.out.println("Flight ID       : " + flight.getFlightId());
            System.out.println("Flight Name     : " + flight.getFlightName());
            System.out.println("Source          : " + flight.getSource());
            System.out.println("Destination     : " + flight.getDestination());
            System.out.println("Departure Time  : " + flight.getDepartureTime());
            System.out.println("Arrival Time    : " + flight.getArrivalTime());
            System.out.println("Travel Date     : " + flight.getTravelDate());
            System.out.println("Ticket Price    : ₹" + flight.getPrice());
            System.out.println("Available Seats : " + flight.getAvailableSeats());
        }

        System.out.println("----------------------------------------------");
    }

    public boolean updateAvailableSeats(int flightId) {

        String query = "UPDATE flights SET available_seats = available_seats - 1 WHERE flight_id = ? AND available_seats > 0";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, flightId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}