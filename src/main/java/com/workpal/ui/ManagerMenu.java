package com.workpal.ui;

import com.workpal.model.Space;
import com.workpal.model.User;
import com.workpal.service.EventService;
import com.workpal.service.SessionManager;
import com.workpal.service.SpaceService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class ManagerMenu {
    SpaceService spaceService = new SpaceService();
    EventService eventService = new EventService();
    public void managerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. Manage my spaces");
            System.out.println("2. Manage reservation");
            System.out.println("3. Manage subscription");
            System.out.println("4. View all events");
            System.out.println("5. View all spaces");
            System.out.println("6. View my notifications");
            System.out.println("8. Search");
            System.out.println("9. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    spacesManagement();
                    break;
                case 2:
                    break;
                case 8:
                    break;
                case 9:
                    return;
            }
        }
    }
    public void spacesManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Spaces management");
        while (true) {
            System.out.println("1. Add a new space");
            System.out.println("2. Update a space");
            System.out.println("3. Delete a space");
            System.out.println("4. View all spaces");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addSpace();
                    break;
                case 2:
                    updateSpace();
                    break;
                case 3:
                    deleteSpace();
                    break;
                case 4:
                    displayAllSpaces();
                    break;
                case 5:
                    return;
            }
        }
    }
    public void addSpace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter space name");
        String name = scanner.nextLine();
        System.out.println("Enter space description");
        String description = scanner.nextLine();
        System.out.println("Enter space policies");
        String policies = scanner.nextLine();
        System.out.println("Enter space type (office, meeting_room)");
        String type = scanner.nextLine();
        User loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            int managerId = loggedInUser.getId();

            try {
                boolean isAdded = spaceService.addSpace(name, description, policies, managerId, type);
                if (isAdded) {
                    System.out.println("Space " + name + " added successfully!");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the space: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
        }

    }



    public void displayAllSpaces() {
        try {
            List<Space> spaces = spaceService.getAllSpaces();
            spaces.forEach(space -> {
                System.out.println("Space ID: " + space.getId());
                System.out.println("Name: " + space.getName());
                System.out.println("Description: " + space.getDescription());
                System.out.println("Policies: " + space.getPolicies());
                System.out.println("Type: " + space.getType());
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching spaces: " + e.getMessage());
        }
    }

    public void updateSpace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choice a space ID from the list");
        displayAllSpaces();
        int spaceId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Edit a space");
        System.out.println("Enter new space name");
        String name = scanner.nextLine();
        System.out.println("Enter new space description");
        String description = scanner.nextLine();
        System.out.println("Enter new space policies");
        String policies = scanner.nextLine();
        System.out.println("Enter new space type (office, meeting_room)");
        String type = scanner.nextLine();

        try {
            Space space = new Space(spaceId, name, description, policies, 0, type);
            boolean isUpdated = spaceService.updateSpace(space);
            if (isUpdated) {
                System.out.println("Space updated successfully!");
            } else {
                System.out.println("Error updating space.");
            }
        } catch (SQLException e) {
            System.out.println("Error during updating the space: " + e.getMessage());
        }
    }

    public void deleteSpace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter space id to delete from the list");
        displayAllSpaces();
        int spaceId = scanner.nextInt();
        try {
            boolean isDeleted = spaceService.deleteSpace(spaceId);
            if (isDeleted) {
                System.out.println("Space deleted successfully!");
            } else {
                System.out.println("Error deleting space.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deletion of the space: " + e.getMessage());
        }
    }
}
