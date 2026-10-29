package interfaces;

import model.Ticket;
import java.util.List;

public interface TicketOperations {

    boolean generateTicket(Ticket ticket);

    Ticket getTicketById(int ticketId);

    List<Ticket> getAllTickets();

    boolean deleteTicket(int ticketId);
}