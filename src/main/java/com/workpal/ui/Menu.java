package com.workpal.ui;

import java.util.Scanner;

public class Menu {
    public void memberMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. View all events");
            System.out.println("2. View all spaces");
            System.out.println("3. View all subscriptions");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter name");
                    break;
                case 2:
            }
        }
    }
    public void managerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to WorkPal");
        while (true) {
            System.out.println("1. ");
        }
    }
}
