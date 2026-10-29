package main;

import service.AdminService;
import service.CustomerService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     AIRLINE RESERVATION SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    adminService.adminMenu();
                    break;

                case 2:
                    customerService.customerMenu();
                    break;

                case 3:
                    System.out.println("Thank You...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}