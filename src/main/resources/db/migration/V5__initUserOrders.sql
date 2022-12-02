drop table user_orders;
CREATE TABLE user_orders(
                                order_id bigint,
                                user_id bigint,
                                car_id bigint,
                                PRIMARY KEY(user_id, order_id),
                                FOREIGN KEY(user_id) REFERENCES users(id),
                                FOREIGN KEY(car_id) REFERENCES car(id)
);