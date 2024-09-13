package com.workpal.repository.interfaces;

import com.workpal.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserInterface {
        User getUserByEmail(String email) throws SQLException;
        void saveUser(User user) throws SQLException;
        String getUserRole(int userId, Connection connection) throws SQLException;
}
