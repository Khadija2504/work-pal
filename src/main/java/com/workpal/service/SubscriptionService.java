package com.workpal.service;


import com.workpal.model.Event;
import com.workpal.model.Subscription;
import com.workpal.repository.SubscriptionRepository;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    SubscriptionRepository subsRepository = new SubscriptionRepository();
    SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    public boolean addSubscription(String name, String description, String type, int price, int manager_id) throws SQLException {
        subsRepository.saveSubscription(new Subscription( 0, name, description, type, price, manager_id));
        return true;
    }

    public List<Subscription> getAllSubscriptions() throws SQLException {
        return subscriptionRepository.getAllSubscriptions();
    }

    public boolean updateSubscription(Subscription subs) throws SQLException {
        return subscriptionRepository.updateSubscription(subs);
    }

    public boolean deleteSubscription(int id) throws SQLException {
        return subscriptionRepository.deleteSubscription(id);
    }
}
