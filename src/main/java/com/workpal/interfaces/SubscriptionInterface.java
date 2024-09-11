package com.workpal.interfaces;

import com.workpal.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionInterface {
    void saveSubscription(Subscription subs) throws SQLException;
    List<Subscription> getAllSubscriptions() throws SQLException;
    boolean updateSubscription(Subscription subs) throws SQLException;
    boolean deleteSubscription(int id) throws SQLException;
}
