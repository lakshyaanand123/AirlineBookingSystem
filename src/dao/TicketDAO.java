package dao;

import database.DBConnection;
import interfaces.TicketOperations;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements TicketOperations {

    private Connection connection;

    public TicketDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean generateTicket(Ticket ticket) {

        String query = "INSERT INTO tickets(booking_id, pnr, seat_number, issue_date) VALUES(?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, ticket.getBookingId());
            ps.setString(2, ticket.getPnr());
            ps.setString(3, ticket.getSeatNumber());
            ps.setDate(4, Date.valueOf(ticket.getIssueDate()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Ticket getTicketById(int ticketId) {

        String query = "SELECT * FROM tickets WHERE ticket_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ticketId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getInt("booking_id"),
                        rs.getString("pnr"),
                        rs.getString("seat_number"),
                        rs.getString("issue_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {

        List<Ticket> tickets = new ArrayList<>();

        String query = "SELECT * FROM tickets";

        try {

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                tickets.add(new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getInt("booking_id"),
                        rs.getString("pnr"),
                        rs.getString("seat_number"),
                        rs.getString("issue_date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public boolean deleteTicket(int ticketId) {

        String query = "DELETE FROM tickets WHERE ticket_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ticketId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}