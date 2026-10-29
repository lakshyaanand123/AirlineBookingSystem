package model;

public class User extends Person {

    public User() {
        super();
    }

    public User(int userId, String name, String email, String password, String phone, String role) {
        super(userId, name, email, password, phone, role);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n===== USER DETAILS =====");
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Phone   : " + phone);
        System.out.println("Role    : " + role);
    }
}