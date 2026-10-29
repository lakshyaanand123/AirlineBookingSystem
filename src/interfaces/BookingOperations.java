package interfaces;

import model.Booking;
import java.util.List;

public interface BookingOperations {

    boolean bookFlight(Booking booking);

    Booking getBookingById(int bookingId);

    List<Booking> getAllBookings();

    boolean cancelBooking(int bookingId);
}