package model;

public class Payment {

    private int paymentId;
    private int bookingId;
    private double amount;
    private String paymentMode;
    private String paymentStatus;
    private String paymentDate;

    // Default Constructor
    public Payment() {

    }

    // Parameterized Constructor
    public Payment(int paymentId, int bookingId, double amount,
                   String paymentMode, String paymentStatus,
                   String paymentDate) {

        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    // Setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Display Payment Details
    public void displayPayment() {

        System.out.println("\n========== PAYMENT DETAILS ==========");
        System.out.println("Payment ID     : " + paymentId);
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Amount         : ₹" + amount);
        System.out.println("Payment Mode   : " + paymentMode);
        System.out.println("Payment Status : " + paymentStatus);
        System.out.println("Payment Date   : " + paymentDate);
        System.out.println("=====================================");
    }
}