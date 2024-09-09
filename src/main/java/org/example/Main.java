package org.example;

import com.workpal.ui.ConsoleUI;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.showMenu();
    }
}