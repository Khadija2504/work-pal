package com.workpal.ui;

import com.workpal.model.*;
import com.workpal.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class ManagerMenu {
    private SpaceService spaceService = null;
    EventService eventService = new EventService();
    SubscriptionService subscriptionService = new SubscriptionService();
    EquipmentService equipmentService = new EquipmentService();
    ServiceService serviceService = new ServiceService();

    public ManagerMenu() {
        this.spaceService = spaceService;
    }

    public void managerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. Manage my spaces");
            System.out.println("2. Manage reservation");
            System.out.println("3. Manage subscription");
            System.out.println("4. Manage events");
            System.out.println("5. Manage equipments");
            System.out.println("6. Manage services");
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
                case 5:
                    equipmentManagement();
                    break;
                case 6:
                    servicesManagement();
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

    public void servicesManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Services management");
        while (true) {
            System.out.println("1. Add a new service");
            System.out.println("2. Update a service");
            System.out.println("3. Delete a service");
            System.out.println("4. View all services");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addService();
                    break;
                case 2:
                    updateService();
                    break;
                case 3:
                    deleteService();
                    break;
                case 4:
                    displayAllServices();
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

    public void equipmentManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Equipments management");
        while (true) {
            System.out.println("1. Add a new equipment");
            System.out.println("2. Update an equipment");
            System.out.println("3. Delete an equipment");
            System.out.println("4. View all equipments");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    updateEquipment();
                    break;
                case 3:
                    deleteEquipment();
                    break;
                case 4:
                    displayALlEquipments();
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
        System.out.println("Enter a service id from the list");
        displayAllServices();
        int service_id = scanner.nextInt();

        System.out.println("Enter space id from the list: ");
        displayAllSpaces();
        int spaceId = scanner.nextInt();

        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();

            try {
                boolean isAdded = subscriptionService.addSubscription(name, description, type, price, spaceId, manager_id, service_id);
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
        System.out.println("Enter pace price");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter space tail");
        int tail = Integer.parseInt(scanner.nextLine());
        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int managerId = loggedInUser.getId();

            try {
                boolean isAdded = spaceService.addSpace(name, description, policies, managerId, type, price, tail);
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
        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);

            Date sqlDate = Date.valueOf(localDate);

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

    public void addEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter equipment name");
        String name = scanner.nextLine();
        System.out.println("Enter space id from the list");
        displayAllSpaces();
        int spaceId = Integer.parseInt(scanner.nextLine());
        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            System.out.println(manager_id);

            try {
                boolean isAdded = equipmentService.addEquipment(name, spaceId, manager_id);
                if (isAdded) {
                    System.out.println("Equipment " + name + " added successfully!");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the equipment: " + e.getMessage());
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
                System.out.println("Price: " + space.getPrice() + "$");
                System.out.println("Tail: " + space.getTail() + "m²");
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching spaces: " + e.getMessage());
        }
    }

    public void displayAllSubs() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

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


    public void displayALlEquipments() {
        try {
            List<Equipment> equipments = equipmentService.getAllEquipments();
            equipments.forEach(equipment -> {
                System.out.println("Space ID: " + equipment.getId());
                System.out.println("Name: " + equipment.getName());
                try {
                    Space space = spaceService.getSpace(equipment.getSpace_id());
                    System.out.println("Space name: " + space.getName());
                    System.out.println("Space type: " + space.getType());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching equipments: " + e.getMessage());
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
        System.out.println("Enter new space price");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new space tail");
        int tail = Integer.parseInt(scanner.nextLine());

        try {
            Space space = new Space(spaceId, name, description, policies, 0, type, price, tail);
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
        System.out.println("Enter subscription type ('monthly', 'annual', 'weekly')");
        String type = scanner.nextLine();
        System.out.println("Enter subscription price $");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new subscription description");
        String description = scanner.nextLine();
        System.out.println("Enter space id from the list: ");
        displayAllSpaces();
        int spaceId = scanner.nextInt();

        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            try {
                Subscription subs = new Subscription(eventId, name, description, type, price, spaceId, manager_id);
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

        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);
            Date sqlDate = Date.valueOf(localDate);

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

    public void updateEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choice an equipment ID from the list");
        displayALlEquipments();
        int equipmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Edit an Equipment");
        System.out.println("Enter new equipment name");
        String name = scanner.nextLine();
        System.out.println("Enter space id from the list");
        displayAllSpaces();
        int space_id = Integer.parseInt(scanner.nextLine());

        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();

            try {
                Equipment equipment = new Equipment(equipmentId, name, space_id, manager_id);
                boolean isUpdated = equipmentService.updateEquipment(equipment);
                if (isUpdated) {
                    System.out.println("equipment updated successfully!");
                } else {
                    System.out.println("Error updating equipment.");
                }

            } catch (SQLException e) {
                System.out.println("Error during updating the equipment " + e.getMessage());
            }
        }
        
    }
    public void deleteEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter equipment id to delete from the list");
        displayALlEquipments();
        int equipementId = scanner.nextInt();
        try {
            boolean isDeleted = equipmentService.deleteEquipment(equipementId);
            if (isDeleted)  {
                System.out.println("equipment deleted successfully!");
            } else {
                System.out.println("Error deleting equipment.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deleting the equipment: " + e.getMessage());
        }
    }

    public void addService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name");
        String name = scanner.nextLine();
        System.out.println("Enter event description");
        String description = scanner.nextLine();
        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();

            try {
                boolean isAdded = serviceService.addService(name, description, manager_id);
                if (isAdded) {
                    System.out.println("Service " + name + " added successfully!");
                } else {
                    System.out.println("Invalid format data");
                }
            } catch (SQLException e) {
                System.out.println("Error during addition of the service: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void updateService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choice a service ID from the list");
        displayAllServices();
        int serviceId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Edit a service");
        System.out.println("Enter new service name");
        String name = scanner.nextLine();
        System.out.println("Enter a service description");
        String description = scanner.nextLine();

        User loggedInUser = SessionUser.getLoggedInUser();

        if (loggedInUser != null) {
            int manager_id = loggedInUser.getId();

            try {
                Service service = new Service(serviceId, name, description, manager_id);
                boolean isUpdated = serviceService.updateService(service);
                if (isUpdated) {
                    System.out.println("service updated successfully!");
                } else {
                    System.out.println("Error updating service");
                }

            } catch (SQLException e) {
                System.out.println("Error during updating the service " + e.getMessage());
            }
        }

    }

    public void displayAllServices() {
        try {
            List<Service> services = serviceService.getAllServices();
            services.forEach(service -> {
                System.out.println("service ID: " + service.getId());
                System.out.println("Name: " + service.getName());
                System.out.println("Description: " + service.getDescription());
                System.out.println("------------");
            });
        } catch (SQLException e) {
            System.out.println("Error fetching services: " + e.getMessage());
        }
    }

    public void deleteService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter service id to delete from the list");
        displayAllServices();
        int serviceId = scanner.nextInt();
        try {
            boolean isDeleted = serviceService.deleteService(serviceId);
            if (isDeleted)  {
                System.out.println("service deleted successfully!");
            } else {
                System.out.println("Error deleting service.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deleting the service: " + e.getMessage());
        }
    }

}
