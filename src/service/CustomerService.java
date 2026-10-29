package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.PaymentDAO;
import dao.TicketDAO;
import dao.UserDAO;
import util.TicketFileGenerator;
import util.BookingHistory;
import model.Booking;
import model.Flight;
import model.Payment;
import model.Ticket;
import model.User;
import util.WaitingList;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerService {

    private Scanner sc = new Scanner(System.in);

    private UserDAO userDAO = new UserDAO();
    private FlightDAO flightDAO = new FlightDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    public void customerMenu() {

        while (true) {

            System.out.println("\n========== CUSTOMER MENU ==========");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private void register() {

        User user = new User();

        System.out.print("Name : ");
        user.setName(sc.nextLine());

        System.out.print("Email : ");
        user.setEmail(sc.nextLine());

        System.out.print("Password : ");
        user.setPassword(sc.nextLine());

        System.out.print("Phone : ");
        user.setPhone(sc.nextLine());

        user.setRole("CUSTOMER");

        if (userDAO.registerUser(user)) {

            System.out.println("\nRegistration Successful.");

        } else {

            System.out.println("\nRegistration Failed.");
        }
    }

    private void login() {

        System.out.print("Email : ");
        String email = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        User user = userDAO.loginUser(email, password);

        if (user != null && user.getRole().equalsIgnoreCase("CUSTOMER")) {

            System.out.println("\nLogin Successful.");
            System.out.println("Welcome " + user.getName());

            customerDashboard(user);

        } else {

            System.out.println("\nInvalid Email or Password.");
        }
    }
        private void customerDashboard(User user) {

        while (true) {

            System.out.println("\n========== CUSTOMER DASHBOARD ==========");
            System.out.println("1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. Make Payment");
            System.out.println("4. Generate Ticket");
            System.out.println("5. View My Bookings");
            System.out.println("6. Logout");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    searchFlights();
                    break;

                case 2:
                    bookFlight(user);
                    break;

                case 3:
                    makePayment();
                    break;

                case 4:
                    generateTicket();
                    break;

                case 5:
                    viewMyBookings(user);
                    break;

                case 6:
                    System.out.println("\nLogging Out...");
                    return;

                default:
                    System.out.println("\nInvalid Choice.");
            }
        }
    }

    private void searchFlights() {

        List<Flight> flights = flightDAO.getAllFlights();

        if (flights.isEmpty()) {

            System.out.println("\nNo Flights Available.");
            return;
        }

        System.out.println("\n==============================================================================================================");

        System.out.printf("%-5s %-20s %-15s %-15s %-12s %-12s %-12s %-10s %-8s%n",
                "ID",
                "Flight",
                "Source",
                "Destination",
                "Departure",
                "Arrival",
                "Date",
                "Price",
                "Seats");

        System.out.println("==============================================================================================================");

        for (Flight flight : flights) {

            System.out.printf("%-5d %-20s %-15s %-15s %-12s %-12s %-12s %-10.2f %-8d%n",
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

        System.out.println("==============================================================================================================");
    }
        private void bookFlight(User user) {

        searchFlights();

        System.out.print("\nEnter Flight ID : ");
        int flightId = sc.nextInt();
        sc.nextLine();

        Flight flight = flightDAO.getFlightById(flightId);

        if (flight == null) {

            System.out.println("\nFlight Not Found.");
            return;
        }

        if (flight.getAvailableSeats() <= 0) {

            System.out.println("\nSorry! No Seats Available.");
            WaitingList.addCustomer(user);
            return;
        }

        Booking booking = new Booking();

        booking.setUserId(user.getUserId());
        booking.setFlightId(flightId);

        System.out.print("Passenger Name : ");
        booking.setPassengerName(sc.nextLine());

        System.out.print("Seat Number : ");
        booking.setSeatNumber(sc.nextLine());

        booking.setBookingDate(LocalDate.now().toString());
        booking.setBookingStatus("CONFIRMED");



if (bookingDAO.bookFlight(booking)) {

    BookingHistory.addBooking(booking);

    flight.setAvailableSeats(flight.getAvailableSeats() - 1);
    flightDAO.updateFlight(flight);

    System.out.println("\nBooking Successful.");

} else {

    System.out.println("\nBooking Failed.");
}
    }

    private void makePayment() {

        System.out.print("\nEnter Booking ID : ");
        int bookingId = sc.nextInt();
        sc.nextLine();

        Booking booking = bookingDAO.getBookingById(bookingId);

        if (booking == null) {

            System.out.println("\nBooking Not Found.");
            return;
        }

        Flight flight = flightDAO.getFlightById(booking.getFlightId());

        if (flight == null) {

            System.out.println("\nFlight Not Found.");
            return;
        }

        Payment payment = new Payment();

        payment.setBookingId(bookingId);
        payment.setAmount(flight.getPrice());

        System.out.println("\nAmount : ₹" + flight.getPrice());

        System.out.print("Payment Mode (UPI/Card/Cash) : ");
        payment.setPaymentMode(sc.nextLine());

        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDate.now().toString());

        if (paymentDAO.makePayment(payment)) {

            System.out.println("\n================================");
            System.out.println("Payment Successful.");
            System.out.println("================================");

        } else {

            System.out.println("\nPayment Failed.");
        }
    }
        private void generateTicket() {

        System.out.print("\nEnter Booking ID : ");
        int bookingId = sc.nextInt();
        sc.nextLine();

        Booking booking = bookingDAO.getBookingById(bookingId);

        if (booking == null) {

            System.out.println("\nBooking Not Found.");
            return;
        }

        Ticket ticket = new Ticket();

        ticket.setBookingId(bookingId);

        Random random = new Random();
        String pnr = "PNR" + (100000 + random.nextInt(900000));

        ticket.setPnr(pnr);
        ticket.setSeatNumber(booking.getSeatNumber());
        ticket.setIssueDate(LocalDate.now().toString());

        if (ticketDAO.generateTicket(ticket)) {

    Payment payment = paymentDAO.getPaymentByBookingId(bookingId);
    Flight flight = flightDAO.getFlightById(booking.getFlightId());

    TicketFileGenerator.saveTicket(
            ticket,
            booking,
            flight,
            payment
    );

    System.out.println("\n================================");
    System.out.println("Ticket Generated Successfully");
    System.out.println("PNR : " + pnr);
    System.out.println("================================");

} else {

    System.out.println("\nTicket Generation Failed.");
}
    }

    private void viewMyBookings(User user) {

        List<Booking> bookings = bookingDAO.getAllBookings();

        boolean found = false;

        System.out.println("\n================ MY BOOKINGS ================");

        for (Booking booking : bookings) {

            if (booking.getUserId() == user.getUserId()) {

                found = true;

                System.out.println("------------------------------------------");
                System.out.println("Booking ID     : " + booking.getBookingId());
                System.out.println("Flight ID      : " + booking.getFlightId());
                System.out.println("Passenger Name : " + booking.getPassengerName());
                System.out.println("Seat Number    : " + booking.getSeatNumber());
                System.out.println("Booking Date   : " + booking.getBookingDate());
                System.out.println("Status         : " + booking.getBookingStatus());
            }
        }

        if (!found) {

            System.out.println("No Bookings Found.");
        }

        System.out.println("------------------------------------------");
    }

}