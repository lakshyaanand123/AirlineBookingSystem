package model;

public class Ticket {

    private int ticketId;
    private int bookingId;
    private String pnr;
    private String seatNumber;
    private String issueDate;

    // Default Constructor
    public Ticket() {

    }

    // Parameterized Constructor
    public Ticket(int ticketId, int bookingId, String pnr,
                  String seatNumber, String issueDate) {

        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.pnr = pnr;
        this.seatNumber = seatNumber;
        this.issueDate = issueDate;
    }

    // Getters
    public int getTicketId() {
        return ticketId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getPnr() {
        return pnr;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    // Setters
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    // Display Ticket Details
    public void displayTicket() {

        System.out.println("\n========== TICKET ==========");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Booking ID  : " + bookingId);
        System.out.println("PNR         : " + pnr);
        System.out.println("Seat Number : " + seatNumber);
        System.out.println("Issue Date  : " + issueDate);
        System.out.println("============================");
    }
}