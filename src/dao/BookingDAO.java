package dao;

import database.DBConnection;
import interfaces.BookingOperations;
import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements BookingOperations {

    private Connection connection;

    public BookingDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean bookFlight(Booking booking) {

        String query = "INSERT INTO bookings(user_id, flight_id, passenger_name, seat_number, booking_date, booking_status) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getFlightId());
            ps.setString(3, booking.getPassengerName());
            ps.setString(4, booking.getSeatNumber());
            ps.setDate(5, Date.valueOf(booking.getBookingDate()));
            ps.setString(6, booking.getBookingStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Booking getBookingById(int bookingId) {

        String query = "SELECT * FROM bookings WHERE booking_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flight_id"),
                        rs.getString("passenger_name"),
                        rs.getString("seat_number"),
                        rs.getString("booking_date"),
                        rs.getString("booking_status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String query = "SELECT * FROM bookings";

        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flight_id"),
                        rs.getString("passenger_name"),
                        rs.getString("seat_number"),
                        rs.getString("booking_date"),
                        rs.getString("booking_status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    @Override
    public boolean cancelBooking(int bookingId) {

        String query = "DELETE FROM bookings WHERE booking_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, bookingId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}