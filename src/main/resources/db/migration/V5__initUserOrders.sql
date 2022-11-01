CREATE TABLE user_orders(
                                order_id INT,
                                user_id INT,
                                car_id INT,
                                PRIMARY KEY(user_id, order_id),
                                FOREIGN KEY(user_id) REFERENCES users(id),
                                FOREIGN KEY(car_id) REFERENCES car(id)
);