package com.workpal.ui;

import com.workpal.model.User;
import com.workpal.repository.implementInterfaces.SubscriptionRepository;
import com.workpal.service.AuthService;
import com.workpal.service.SessionUser;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private AuthService authService = new AuthService();
    private User loggedInUser = null;
    private MemberMenu memberMenu;
    private ManagerMenu managerMenu;
    private AdminMenu adminMenu;

    public ConsoleUI() {
        this.memberMenu = new MemberMenu();
        this.managerMenu = new ManagerMenu();
        this.adminMenu = new AdminMenu();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to WorkPal");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        System.out.println("Enter your phone:");
        String phone = scanner.nextLine();
        System.out.println("Select your role (manager, member):");
        String role = scanner.nextLine();

        try {
            boolean isRegistered = authService.register(name, email, password, address, phone, role);
            if (isRegistered) {
                System.out.println("Registration successful as " + role + "!");
            } else {
                System.out.println("User with this email already exists.");
            }
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        try {
            Optional<User> user = authService.login(email, password);
            if (user.isPresent()) {
                loggedInUser = user.get();
                SessionUser.setLoggedInUser(loggedInUser);
                System.out.println("Login successful! Welcome " + loggedInUser.getName() + loggedInUser.getRole());
                if(loggedInUser.getRole().equals("manager")) {
                    managerMenu.managerMenu();
                } else {
                    memberMenu.memberMenu();
                }
                }
            else {
                System.out.println("Invalid email or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    private void showLoggedInMenu() {
        Scanner scanner = new Scanner(System.in);
        while (loggedInUser != null) {
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewProfile();
                case 2 -> logout();
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewProfile() {
        if (loggedInUser != null) {
            System.out.println("Profile Information:");
            System.out.println("Name: " + loggedInUser.getName());
            System.out.println("Email: " + loggedInUser.getEmail());
        } else {
            System.out.println("You are not logged in.");
        }
    }

    private void logout() {
        if (loggedInUser != null) {
            System.out.println("Logging out " + loggedInUser.getName());
            loggedInUser = null;
        } else {
            System.out.println("You are not logged in.");
        }
    }

}
