package dao;

import database.DBConnection;
import interfaces.PaymentOperations;
import model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements PaymentOperations {

    private Connection connection;

    public PaymentDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean makePayment(Payment payment) {

        String query = "INSERT INTO payments(booking_id, amount, payment_mode, payment_status, payment_date) VALUES(?,?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMode());
            ps.setString(4, payment.getPaymentStatus());
            ps.setDate(5, Date.valueOf(payment.getPaymentDate()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Payment getPaymentById(int paymentId) {

        String query = "SELECT * FROM payments WHERE payment_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_mode"),
                        rs.getString("payment_status"),
                        rs.getString("payment_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        String query = "SELECT * FROM payments";

        try {

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                payments.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_mode"),
                        rs.getString("payment_status"),
                        rs.getString("payment_date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    @Override
    public boolean deletePayment(int paymentId) {

        String query = "DELETE FROM payments WHERE payment_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, paymentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public Payment getPaymentByBookingId(int bookingId) {

    String query = "SELECT * FROM payments WHERE booking_id=?";

    try {

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, bookingId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("booking_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_mode"),
                    rs.getString("payment_status"),
                    rs.getString("payment_date")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}