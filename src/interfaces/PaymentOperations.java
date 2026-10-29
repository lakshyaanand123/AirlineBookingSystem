package interfaces;

import model.Payment;
import java.util.List;

public interface PaymentOperations {

    boolean makePayment(Payment payment);

    Payment getPaymentById(int paymentId);

    List<Payment> getAllPayments();

    boolean deletePayment(int paymentId);
}