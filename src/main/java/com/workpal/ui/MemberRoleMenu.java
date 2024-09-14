package com.workpal.ui;

import com.workpal.model.*;
import com.workpal.service.SessionUser;
import com.workpal.service.SpacesReservationService;
import com.workpal.service.SubsMemberService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberRoleMenu {
    private SubsMemberService subsMemberService = new SubsMemberService();
    private SpacesReservationService spacesReservationService = new SpacesReservationService();
    public void memberRoleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. Manage my profile");
            System.out.println("2. Manage reservation");
            System.out.println("3. Manage subscription");
            System.out.println("4. View all events");
            System.out.println("5. View all spaces");
            System.out.println("6. View my notifications");
            System.out.println("7. My favorite spaces");
            System.out.println("8. Search");
            System.out.println("Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    profileManagementMenu();
                    break;
                case 2:
                    ReservationManagementMenu();
                    break;
                case 5:
                    displayAllSpaces();
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

    public void ReservationManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Reserve an event");
            System.out.println("2. Reserve a space");
            System.out.println("3. Reserve a subscription");
            System.out.println("4. Cancel a reservation event");
            System.out.println("5. Cancel a reservation space");
            System.out.println("6. View pending reservations ");
            System.out.println("7. view All subscriptions reservations");
            System.out.println("8. My future reservation history");
            System.out.println("9. My past reservation history");
            System.out.println("10. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    spaceReservation();
                    break;
                case 3:
                    SubsReservation();
                    break;
                case 6:
                    displayAllReservations();
                    break;
                case 7:
                    displayAllSubsMembers();
                    break;
                case 10:
                    continue;
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

    public void displayAllReservations () {
        try {
            List<Space> reservations = spacesReservationService.getAllSpaceReservations();

            reservations.forEach(reservation -> {
                System.out.println("Subscription ID: " + reservation.getId());
                System.out.println("Name: " + reservation.getName());
                System.out.println("Description: " + reservation.getDescription());
                System.out.println("Type: " + reservation.getType());
                System.out.println("Price: " + reservation.getPrice() + " $");
                System.out.println("Tail: " + reservation.getTail() + " m²");

                System.out.println("Reservation status");
                List<SpaceReservation> spaceReserved = reservation.getSpaceReservations();
                if (spaceReserved.isEmpty()) {
                    System.out.println("  No reservations associated with this space.");
                } else {
                    spaceReserved.forEach(service -> {
                        System.out.println("  reservation ID: " + service.getId());
                        System.out.println("  reservation Name: " + service.getStatus());
                        System.out.println("  --------");
                    });
                }

                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching reservations: " + e.getMessage());
        }
    }
    public void displayAllSpaces() {
        try {
            List<Space> spaces = spacesReservationService.getAllSpaces();
            spaces.forEach(space -> {
                System.out.println("Space ID: " + space.getId());
                System.out.println("Name: " + space.getName());
                System.out.println("Description: " + space.getDescription());
                System.out.println("Policies: " + space.getPolicies());
                System.out.println("Type: " + space.getType());
                System.out.println("Price " + space.getPrice());
                System.out.println("Tail " + space.getTail());
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching spaces: " + e.getMessage());
        }
    }

    public void displayAllSubs() {
        try {
            List<Subscription> subscriptions = subsMemberService.getAllSubscriptions();

            subscriptions.forEach(subscription -> {
                System.out.println("Subscription ID: " + subscription.getId());
                System.out.println("Name: " + subscription.getName());
                System.out.println("Description: " + subscription.getDescription());
                System.out.println("Type: " + subscription.getType());
                System.out.println("Price: " + subscription.getPrice() + "$");

                System.out.println("Services:");
                List<Service> services = subscription.getServices();
                if (services.isEmpty()) {
                    System.out.println("  No services associated with this subscription.");
                } else {
                    services.forEach(service -> {
                        System.out.println("  Service ID: " + service.getId());
                        System.out.println("  Service Name: " + service.getName());
                        System.out.println("  Service Description: " + service.getDescription());
                        System.out.println("  --------");
                    });
                }

                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching subscriptions: " + e.getMessage());
        }
    }

    public void spaceReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter space id from the list ");
        displayAllSpaces();
        int spaceId = scanner.nextInt();
        System.out.println("Print hello world");
        String hello = scanner.nextLine();
        System.out.println("Enter your card infos");
        String cardInfo = scanner.nextLine();
        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int memberId = loggedInUser.getId();

            try {
                boolean isAdded = spacesReservationService.addSpaceReservation(memberId, spaceId, cardInfo);
                if (isAdded) {
                    System.out.println("Space reservation added successfully! we'll notify you when the manager accept your reservation");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the reservation: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void SubsReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter subscription id from the list ");
        displayAllSubs();
        int subsId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your card infos");
        String cardInfo = scanner.nextLine();
        User loggedInUser = SessionUser.getLoggedInUser();
        System.out.println(subsId + cardInfo);

        if (loggedInUser != null) {
            int memberId = loggedInUser.getId();

            try {
                boolean isAdded = subsMemberService.addSubsMember(memberId, subsId, cardInfo);
                if (isAdded) {
                    System.out.println("Subscription reservation added successfully! we'll notify you with the reservation confirmation");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the reservation: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void displayAllSubsMembers() {
        try {
            List<Subscription> subscriptions = subsMemberService.getAllSubsMembers();

            subscriptions.forEach(subscription -> {
                System.out.println("Subscription ID: " + subscription.getId());
                System.out.println("Name: " + subscription.getName());
                System.out.println("Description: " + subscription.getDescription());
                System.out.println("Type: " + subscription.getType());
                System.out.println("Price: " + subscription.getPrice() + "$");

                System.out.println("Members' Reservations:");
                List<SubsMember> subsMembers = subscription.getSubsMembers();
                if (subsMembers.isEmpty()) {
                    System.out.println("  No members associated with this subscription.");
                } else {
                    subsMembers.forEach(subsMember -> {
                        System.out.println("  Member ID: " + subsMember.getMember_id());
                        System.out.println("  Reservation status: " + subsMember.getAvailability());
                        System.out.println("  --------");
                    });
                }

                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching subscriptions: " + e.getMessage());
        }
    }

    public void cancelReservationSubs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reservation subs id to delete from the list");
        displayAllSpaces();
        int subsId = scanner.nextInt();
        try {
            boolean isDeleted = subsMemberService.deleteReservation(subsId);
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
