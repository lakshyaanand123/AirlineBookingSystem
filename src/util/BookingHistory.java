package util;

import model.Booking;

import java.util.Stack;

public class BookingHistory {

    private static Stack<Booking> bookingStack = new Stack<>();

    public static void addBooking(Booking booking) {

        bookingStack.push(booking);
    }

    public static void showHistory() {

        if (bookingStack.isEmpty()) {

            System.out.println("\nNo Booking History Available.");
            return;
        }

        System.out.println("\n========== RECENT BOOKINGS ==========");

        Stack<Booking> temp = (Stack<Booking>) bookingStack.clone();

        while (!temp.isEmpty()) {

            Booking booking = temp.pop();

            System.out.println("----------------------------------");
            System.out.println("Booking ID     : " + booking.getBookingId());
            System.out.println("User ID        : " + booking.getUserId());
            System.out.println("Flight ID      : " + booking.getFlightId());
            System.out.println("Passenger Name : " + booking.getPassengerName());
            System.out.println("Seat Number    : " + booking.getSeatNumber());
            System.out.println("Booking Date   : " + booking.getBookingDate());
            System.out.println("Status         : " + booking.getBookingStatus());
        }

        System.out.println("----------------------------------");
    }
}