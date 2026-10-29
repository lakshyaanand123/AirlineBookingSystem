package util;

import model.Booking;
import model.Flight;
import model.Payment;
import model.Ticket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketFileGenerator {

    public static void saveTicket(Ticket ticket,
                                  Booking booking,
                                  Flight flight,
                                  Payment payment) {

        try {

            File folder = new File("tickets");

            if (!folder.exists()) {
                folder.mkdir();
            }

            String fileName = "tickets/Ticket_" + ticket.getPnr() + ".txt";

            FileWriter writer = new FileWriter(fileName);

            writer.write("=====================================\n");
            writer.write("        AIRLINE TICKET\n");
            writer.write("=====================================\n\n");

            writer.write("PNR            : " + ticket.getPnr() + "\n");
            writer.write("Booking ID     : " + booking.getBookingId() + "\n");
            writer.write("Passenger Name : " + booking.getPassengerName() + "\n");
            writer.write("Seat Number    : " + booking.getSeatNumber() + "\n\n");

            writer.write("Flight Name    : " + flight.getFlightName() + "\n");
            writer.write("Source         : " + flight.getSource() + "\n");
            writer.write("Destination    : " + flight.getDestination() + "\n");
            writer.write("Travel Date    : " + flight.getTravelDate() + "\n");
            writer.write("Departure Time : " + flight.getDepartureTime() + "\n");
            writer.write("Arrival Time   : " + flight.getArrivalTime() + "\n\n");

            writer.write("Amount Paid    : ₹" + payment.getAmount() + "\n");
            writer.write("Payment Mode   : " + payment.getPaymentMode() + "\n");
            writer.write("Payment Status : " + payment.getPaymentStatus() + "\n\n");

            writer.write("Ticket Issued  : " + ticket.getIssueDate() + "\n");

            writer.write("\n=====================================\n");
            writer.write("Thank You For Choosing Our Airline\n");
            writer.write("Have a Safe Journey!\n");
            writer.write("=====================================\n");

            writer.close();

            System.out.println("\nTicket saved successfully.");
            System.out.println("Location : " + fileName);

        } catch (IOException e) {

            System.out.println("Unable to save ticket.");
            e.printStackTrace();
        }
    }
}