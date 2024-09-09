package com.workpal.ui;

import java.util.Scanner;

public class MemberMenu {
    public void memberMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. Manage my profile"); // done
            System.out.println("2. Manage reservation");
            System.out.println("3. Manage subscription");
            System.out.println("4. View all events");
            System.out.println("5. View all spaces");
            System.out.println("6. View my notifications");
            System.out.println("7. My favorite spaces");
            System.out.println("8. Search"); // done
            System.out.println("Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    profileManagementMenu();
                    break;
                case 2:
                    ReservationMnagementMenu();
                    break;
                case 8:
                    profileManagementMenu();
                    break;
            }
        }
    }

    public void subscriptionManagementMenu() {
        Scanner scanner = new Scanner(System.in);
    }

    public void ReservationMnagementMenu() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Reserve an event");
            System.out.println("2. Reserve a space");
            System.out.println("3. Cancel a reservation event");
            System.out.println("4. Cancel a reservation space");
            System.out.println("5. My future reservation history");
            System.out.println("6. My past reservation history");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void profileManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Update my profile");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void searchMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Search spaces");
            System.out.println("2. Search events");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    searchSpacesMenu();
                    break;
                case 2:
                    searchEventMenu();
                break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    public void searchSpacesMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Search by type");
            System.out.println("2. Search by date");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                break;
                default :
                    System.out.println("Invalid option");
            }
        }
    }
    public void searchEventMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Search by type");
            System.out.println("2. Search date");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                default :
                    System.out.println("Invalid option");
            }
        }
    }
}
