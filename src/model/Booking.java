package model;

public class Booking {

    private int bookingId;
    private int userId;
    private int flightId;
    private String passengerName;
    private String seatNumber;
    private String bookingDate;
    private String bookingStatus;

    public Booking() {

    }

    public Booking(int bookingId,
                   int userId,
                   int flightId,
                   String passengerName,
                   String seatNumber,
                   String bookingDate,
                   String bookingStatus) {

        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void displayBooking() {

        System.out.println("\n========== BOOKING DETAILS ==========");
        System.out.println("Booking ID      : " + bookingId);
        System.out.println("User ID         : " + userId);
        System.out.println("Flight ID       : " + flightId);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Seat Number     : " + seatNumber);
        System.out.println("Booking Date    : " + bookingDate);
        System.out.println("Booking Status  : " + bookingStatus);
        System.out.println("=====================================");
    }
}