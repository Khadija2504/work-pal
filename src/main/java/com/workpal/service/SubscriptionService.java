package com.workpal.service;


import com.workpal.model.SubsService;
import com.workpal.model.Subscription;
import com.workpal.repository.implementInterfaces.SubscriptionRepository;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    SubscriptionRepository subsRepository = new SubscriptionRepository();
    SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    public boolean addSubscription(String name, String description, String type, int price, int manager_id, int service_id) throws SQLException {
        subsRepository.saveSubscription(new Subscription( 0, name, description, type, price, manager_id), new SubsService(0, service_id, manager_id));
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
