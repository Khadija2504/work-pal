CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       address VARCHAR(255),
                       phone VARCHAR(13),
                       role VARCHAR(13)
);

CREATE TABLE admin (
                       user_id INT PRIMARY KEY REFERENCES users(id)
) INHERITS (users);

CREATE TABLE managers (
                          user_id INT PRIMARY KEY REFERENCES users(id),
                          statistics_view_access BOOLEAN DEFAULT TRUE
) INHERITS (users);

CREATE TABLE members (
                         user_id INT PRIMARY KEY REFERENCES users(id),
                         profile_picture VARCHAR(255)
) INHERITS (users);

CREATE TABLE spaces (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE,
                        type VARCHAR(50) CHECK (type IN ('office', 'meeting room')),
                        policies TEXT,
                        price INT,
                        tail INT,
                        description TEXT
);

CREATE TABLE reservations_space (
                                    id SERIAL PRIMARY KEY,
                                    member_id INT REFERENCES members(user_id) ON DELETE CASCADE,
                                    space_id INT REFERENCES spaces(id),
                                    status VARCHAR(50) CHECK (status IN ('confirmed', 'cancelled', 'not_yet')) DEFAULT 'not_yet',
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE events (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        description TEXT,
                        type VARCHAR(50),
                        date DATE,
                        policies TEXT,
                        location VARCHAR(255),
                        places_num INT,
                        manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE
);

CREATE TABLE reservations_events (
                                     id SERIAL PRIMARY KEY,
                                     member_id INT REFERENCES members(user_id) ON DELETE CASCADE,
                                     event_id INT REFERENCES events(id),
                                     reservation_date DATE,
                                     reservation_time TIME,
                                     status VARCHAR(50) CHECK (status IN ('confirmed', 'cancelled')),
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE equipments (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255),
                            space_id INT REFERENCES spaces(id),
                            manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE

);

CREATE TABLE services (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          description TEXT,
                          manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE
);

CREATE TABLE otherServices (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               description TEXT,
                               manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE
);

CREATE TABLE subscriptions (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               description TEXT,
                               price DECIMAL(10, 2),
                               manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE,
                               type VARCHAR(50) CHECK (type IN ('monthly', 'annual', 'weekly'))
);

CREATE TABLE subs_services (
                               id SERIAL PRIMARY KEY,
                               service_id INT REFERENCES services(id) ON DELETE CASCADE,
                               manager_id INT REFERENCES managers(user_id) ON DELETE CASCADE,
                               subs_id INT REFERENCES subscriptions(id)
);

CREATE TABLE space_payments (
                                id SERIAL PRIMARY KEY,
                                member_id INT REFERENCES members(user_id),
                                card_info VARCHAR(255),
                                subs_id INT REFERENCES subscriptions(id) ON DELETE CASCADE,
                                payment_type VARCHAR(50) CHECK (payment_type IN ('subscription', 'event', 'space')),
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE subs_members (
                              id SERIAL PRIMARY KEY,
                              subscription_id INT REFERENCES subscriptions(id),
                              user_id INT REFERENCES members(user_id) ON DELETE CASCADE,
                              availability BOOLEAN DEFAULT TRUE
);

CREATE TABLE subs_payments (
                               id SERIAL PRIMARY KEY,
                               member_id INT REFERENCES members(user_id),
                               card_info VARCHAR(255),
                               space_id INT REFERENCES subscriptions(id) ON DELETE CASCADE,
                               payment_type VARCHAR(50) CHECK (payment_type IN ('subscription', 'event', 'space')),
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,
                               member_id INT REFERENCES users(id),
                               admin_id INT REFERENCES users(id),
                               manager_id INT REFERENCES users(id),
                               description TEXT,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
