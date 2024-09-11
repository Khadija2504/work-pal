package com.workpal.ui;

import com.workpal.model.Event;
import com.workpal.model.Space;
import com.workpal.model.Subscription;
import com.workpal.model.User;
import com.workpal.service.EventService;
import com.workpal.service.SessionManager;
import com.workpal.service.SpaceService;
import com.workpal.service.SubscriptionService;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class ManagerMenu {
    SpaceService spaceService = new SpaceService();
    EventService eventService = new EventService();
    SubscriptionService subscriptionService = new SubscriptionService();
    public void managerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. Manage my spaces");
            System.out.println("2. Manage reservation");
            System.out.println("3. Manage subscription");
            System.out.println("4. Manage events");
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
                case 3:
                    subsManagement();
                    break;
                case 4:
                    eventsManagement();
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

    public void eventsManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Events management");
        while (true) {
            System.out.println("1. Add a new event");
            System.out.println("2. Update a event");
            System.out.println("3. Delete a event");
            System.out.println("4. View all event");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    updateEvent();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    displayAllEvents();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void subsManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Subscriptions management");
        while (true) {
            System.out.println("1. Add a new subscription");
            System.out.println("2. Update a subscription");
            System.out.println("3. Delete a subscription");
            System.out.println("4. View all subscription");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addSubs();
                    break;
                case 2:
                    updateSubscription();
                    break;
                case 3:
                    deleteSubscription();
                    break;
                case 4:
                    displayAllSubs();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void addSubs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter subscription name");
        String name = scanner.nextLine();
        System.out.println("Enter subscription description");
        String description = scanner.nextLine();
        System.out.println("Enter subscription type ('monthly', 'annual', 'weekly')");
        String type = scanner.nextLine();
        System.out.println("Enter subscription price");
        int price = Integer.parseInt(scanner.nextLine());
        User loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();

            try {
                boolean isAdded = subscriptionService.addSubscription(name, description, type, price, manager_id);
                if (isAdded) {
                    System.out.println("Subscription " + name + " added successfully!");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the subscription: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
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

    public void addEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name");
        String name = scanner.nextLine();
        System.out.println("Enter event description");
        String description = scanner.nextLine();
        System.out.println("Enter event policies");
        String policies = scanner.nextLine();
        System.out.println("Enter event type");
        String type = scanner.nextLine();
        System.out.println("Enter event date");
        String dateStr = scanner.nextLine();
        System.out.println("Enter event places number");
        int places_num = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter event location");
        String location = scanner.nextLine();
        User loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);

            java.sql.Date sqlDate = Date.valueOf(localDate);

            try {
                boolean isAdded = eventService.addEvent(name, description, location, sqlDate, type, policies, places_num, manager_id);
                if (isAdded) {
                    System.out.println("Event " + name + " added successfully!");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the event: " + e.getMessage());
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

    public void displayAllSubs() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
            subscriptions.forEach(space -> {
                System.out.println("Space ID: " + space.getId());
                System.out.println("Name: " + space.getName());
                System.out.println("Description: " + space.getDescription());
                System.out.println("Type: " + space.getType());
                System.out.println("price: " + space.getPrice() + "$");
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching subs: " + e.getMessage());
        }
    }

    public void displayAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            events.forEach(event -> {
                System.out.println("Event ID: " + event.getId());
                System.out.println("Name: " + event.getName());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Type: " + event.getType());
                System.out.println("places number: " + event.getPlaces_num());
                System.out.println("location: " + event.getLocation());
                System.out.println("Date: " + event.getDate());
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching events: " + e.getMessage());
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
    public void deleteEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event id to delete from the list");
        displayAllEvents();
        int eventId = scanner.nextInt();
        try {
            boolean isDeleted = eventService.deleteEvent(eventId);
            if (isDeleted)  {
                System.out.println("Event deleted successfully!");
            } else {
                System.out.println("Error deleting event.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deleting the event: " + e.getMessage());
        }
    }

    public void deleteSubscription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter subscription id to delete from the list");
        displayAllEvents();
        int subsId = scanner.nextInt();
        try {
            boolean isDeleted = subscriptionService.deleteSubscription(subsId);
            if (isDeleted)  {
                System.out.println("Subscription deleted successfully!");
            } else {
                System.out.println("Error deleting subscription.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deleting the subscription: " + e.getMessage());
        }
    }

    public void updateSubscription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choice an event ID from the list");
        displayAllSubs();
        int eventId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Edit an event");
        System.out.println("Enter new subscription name");
        String name = scanner.nextLine();
        System.out.println("Enter event type ('monthly', 'annual', 'weekly')");
        String type = scanner.nextLine();
        System.out.println("Enter subscription price $");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new subscription description");
        String description = scanner.nextLine();

        User loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            try {
                Subscription subs = new Subscription(eventId, name, description, type, price, manager_id);
                boolean isUpdated = subscriptionService.updateSubscription(subs);
                if (isUpdated) {
                    System.out.println("Subscription updated successfully!");
                } else {
                    System.out.println("Error updating subscription.");
                }

            } catch (SQLException e) {
                System.out.println("Error during updating the subscription: " + e.getMessage());
            }
        }
    }
    public void updateEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choice an event ID from the list");
        displayAllEvents();
        int eventId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Edit an event");
        System.out.println("Enter new event name");
        System.out.println("hello world");
        String name = scanner.nextLine();
        String hello= scanner.nextLine();
        System.out.println("Enter event policies");
        String policies = scanner.nextLine();
        System.out.println("Enter event type");
        String type = scanner.nextLine();
        System.out.println("Enter event date");
        String dateStr = scanner.nextLine();
        System.out.println("Enter event places number");
        int places_num = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter event location");
        String location = scanner.nextLine();
        System.out.println("Enter new event description");
        String description = scanner.nextLine();

        User loggedInUser = SessionManager.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);
            java.sql.Date sqlDate = Date.valueOf(localDate);

            try {
                Event event = new Event(name, description, location, sqlDate, type, policies, places_num, manager_id, eventId);
                boolean isUpdated = eventService.updateEvent(event);
                if (isUpdated) {
                    System.out.println("event updated successfully!");
                } else {
                    System.out.println("Error updating event.");
                }

            } catch (SQLException e) {
                System.out.println("Error during updating the event: " + e.getMessage());
            }
        }
    }
}
