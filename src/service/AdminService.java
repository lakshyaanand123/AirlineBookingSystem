package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.PaymentDAO;
import dao.TicketDAO;
import dao.UserDAO;
import util.BookingHistory;
import model.Booking;
import model.Flight;
import model.Payment;
import model.Ticket;
import model.User;
import util.WaitingList;
import java.util.List;
import java.util.Scanner;

public class AdminService {

    private Scanner sc = new Scanner(System.in);

    private UserDAO userDAO = new UserDAO();
    private FlightDAO flightDAO = new FlightDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    public void adminMenu() {

        while (true) {

            System.out.println("\n========== ADMIN ==========");
            System.out.println("1. Login");
            System.out.println("2. Back");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    login();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private void login() {

        System.out.print("Email : ");
        String email = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        User admin = userDAO.loginUser(email, password);

        if (admin != null && admin.getRole().equalsIgnoreCase("ADMIN")) {

            System.out.println("\n================================");
            System.out.println("Login Successful");
            System.out.println("Welcome " + admin.getName());
            System.out.println("================================");

            adminDashboard();

        } else {

            System.out.println("\nInvalid Admin Credentials.");
        }
    }

    private void adminDashboard() {

        while (true) {

            System.out.println("\n========== ADMIN DASHBOARD ==========");
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Update Flight");
            System.out.println("4. Delete Flight");
            System.out.println("5. View Users");
            System.out.println("6. View Bookings");
            System.out.println("7. View Payments");
            System.out.println("8. View Tickets");
            System.out.println("9. View Booking History");
System.out.println("10. View Waiting List");
System.out.println("11. Logout");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addFlight();
                    break;

                case 2:
                    viewFlights();
                    break;

                case 3:
                    updateFlight();
                    break;

                case 4:
                    deleteFlight();
                    break;

                case 5:
                    viewUsers();
                    break;

                case 6:
                    viewBookings();
                    break;

                case 7:
                    viewPayments();
                    break;

                case 8:
                    viewTickets();
                     break;

                case 9:
    BookingHistory.showHistory();
    break;

case 10:
    WaitingList.showWaitingList();
    break;

case 11:
    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
        private void addFlight() {

        Flight flight = new Flight();

        System.out.print("\nFlight Name : ");
        flight.setFlightName(sc.nextLine());

        System.out.print("Source : ");
        flight.setSource(sc.nextLine());

        System.out.print("Destination : ");
        flight.setDestination(sc.nextLine());

        System.out.print("Departure Time : ");
        flight.setDepartureTime(sc.nextLine());

        System.out.print("Arrival Time : ");
        flight.setArrivalTime(sc.nextLine());

        System.out.print("Travel Date (yyyy-mm-dd) : ");
        flight.setTravelDate(sc.nextLine());

        System.out.print("Ticket Price : ");
        flight.setPrice(sc.nextDouble());

        System.out.print("Total Seats : ");
        flight.setTotalSeats(sc.nextInt());

        flight.setAvailableSeats(flight.getTotalSeats());

        sc.nextLine();

        if (flightDAO.addFlight(flight)) {

            System.out.println("\n================================");
            System.out.println("Flight Added Successfully.");
            System.out.println("================================");

        } else {

            System.out.println("\nFailed to Add Flight.");
        }
    }

    private void viewFlights() {

        List<Flight> flights = flightDAO.getAllFlights();

        if (flights.isEmpty()) {

            System.out.println("\nNo Flights Available.");
            return;
        }

        System.out.println("\n================================================================================================================");

        System.out.printf("%-5s %-20s %-15s %-15s %-10s %-10s %-12s %-10s %-8s%n",
                "ID",
                "Flight",
                "Source",
                "Destination",
                "Departure",
                "Arrival",
                "Date",
                "Price",
                "Seats");

        System.out.println("================================================================================================================");

        for (Flight flight : flights) {

            System.out.printf("%-5d %-20s %-15s %-15s %-10s %-10s %-12s %-10.2f %-8d%n",
                    flight.getFlightId(),
                    flight.getFlightName(),
                    flight.getSource(),
                    flight.getDestination(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getTravelDate(),
                    flight.getPrice(),
                    flight.getAvailableSeats());
        }

        System.out.println("================================================================================================================");
    }
        private void updateFlight() {

        System.out.print("\nEnter Flight ID : ");
        int flightId = sc.nextInt();
        sc.nextLine();

        Flight flight = flightDAO.getFlightById(flightId);

        if (flight == null) {

            System.out.println("\nFlight Not Found.");
            return;
        }

        System.out.print("Flight Name : ");
        flight.setFlightName(sc.nextLine());

        System.out.print("Source : ");
        flight.setSource(sc.nextLine());

        System.out.print("Destination : ");
        flight.setDestination(sc.nextLine());

        System.out.print("Departure Time : ");
        flight.setDepartureTime(sc.nextLine());

        System.out.print("Arrival Time : ");
        flight.setArrivalTime(sc.nextLine());

        System.out.print("Travel Date (yyyy-mm-dd) : ");
        flight.setTravelDate(sc.nextLine());

        System.out.print("Ticket Price : ");
        flight.setPrice(sc.nextDouble());

        System.out.print("Total Seats : ");
        flight.setTotalSeats(sc.nextInt());

        System.out.print("Available Seats : ");
        flight.setAvailableSeats(sc.nextInt());

        sc.nextLine();

        if (flightDAO.updateFlight(flight)) {

            System.out.println("\nFlight Updated Successfully.");

        } else {

            System.out.println("\nFailed to Update Flight.");
        }
    }

    private void deleteFlight() {

        System.out.print("\nEnter Flight ID : ");
        int flightId = sc.nextInt();
        sc.nextLine();

        if (flightDAO.deleteFlight(flightId)) {

            System.out.println("\nFlight Deleted Successfully.");

        } else {

            System.out.println("\nFlight Not Found.");
        }
    }

    private void viewUsers() {

        List<User> users = userDAO.getAllUsers();

        if (users.isEmpty()) {

            System.out.println("\nNo Users Found.");
            return;
        }

        System.out.println("\n==================== USERS ====================");

        for (User user : users) {

            System.out.println("--------------------------------------------");
            System.out.println("User ID : " + user.getUserId());
            System.out.println("Name : " + user.getName());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Phone : " + user.getPhone());
            System.out.println("Role : " + user.getRole());
        }

        System.out.println("--------------------------------------------");
    }

    private void viewBookings() {

        List<Booking> bookings = bookingDAO.getAllBookings();

        if (bookings.isEmpty()) {

            System.out.println("\nNo Bookings Found.");
            return;
        }

        System.out.println("\n================ BOOKINGS =================");

        for (Booking booking : bookings) {

            System.out.println("--------------------------------------------");
            System.out.println("Booking ID : " + booking.getBookingId());
            System.out.println("User ID : " + booking.getUserId());
            System.out.println("Flight ID : " + booking.getFlightId());
            System.out.println("Passenger : " + booking.getPassengerName());
            System.out.println("Seat : " + booking.getSeatNumber());
            System.out.println("Date : " + booking.getBookingDate());
            System.out.println("Status : " + booking.getBookingStatus());
        }

        System.out.println("--------------------------------------------");
    }

    private void viewPayments() {

        List<Payment> payments = paymentDAO.getAllPayments();

        if (payments.isEmpty()) {

            System.out.println("\nNo Payments Found.");
            return;
        }

        System.out.println("\n================ PAYMENTS =================");

        for (Payment payment : payments) {

            System.out.println("--------------------------------------------");
            System.out.println("Payment ID : " + payment.getPaymentId());
            System.out.println("Booking ID : " + payment.getBookingId());
            System.out.println("Amount : ₹" + payment.getAmount());
            System.out.println("Mode : " + payment.getPaymentMode());
            System.out.println("Status : " + payment.getPaymentStatus());
            System.out.println("Date : " + payment.getPaymentDate());
        }

        System.out.println("--------------------------------------------");
    }

    private void viewTickets() {

        List<Ticket> tickets = ticketDAO.getAllTickets();

        if (tickets.isEmpty()) {

            System.out.println("\nNo Tickets Found.");
            return;
        }

        System.out.println("\n================ TICKETS =================");

        for (Ticket ticket : tickets) {

            System.out.println("--------------------------------------------");
            System.out.println("Ticket ID : " + ticket.getTicketId());
            System.out.println("Booking ID : " + ticket.getBookingId());
            System.out.println("PNR : " + ticket.getPnr());
            System.out.println("Seat : " + ticket.getSeatNumber());
            System.out.println("Issue Date : " + ticket.getIssueDate());
        }

        System.out.println("--------------------------------------------");
    }

}