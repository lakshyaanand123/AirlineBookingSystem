package util;

import model.User;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingList {

    private static Queue<User> waitingQueue = new LinkedList<>();

    public static void addCustomer(User user) {

        waitingQueue.offer(user);

        System.out.println("\nCustomer added to Waiting List.");
    }

    public static void showWaitingList() {

        if (waitingQueue.isEmpty()) {

            System.out.println("\nWaiting List is Empty.");
            return;
        }

        System.out.println("\n========== WAITING LIST ==========");

        Queue<User> temp = new LinkedList<>(waitingQueue);

        while (!temp.isEmpty()) {

            User user = temp.poll();

            System.out.println("----------------------------------");
            System.out.println("User ID : " + user.getUserId());
            System.out.println("Name    : " + user.getName());
            System.out.println("Email   : " + user.getEmail());
        }

        System.out.println("----------------------------------");
    }

    public static User nextCustomer() {

        return waitingQueue.poll();
    }
}