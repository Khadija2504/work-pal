package com.workpal.service;

import com.workpal.model.User;

public class SessionUser {
        private static User loggedInUser;

        public static void setLoggedInUser(User user) {
            loggedInUser = user;
        }

        public static User getLoggedInUser() {
            return loggedInUser;
        }
}
