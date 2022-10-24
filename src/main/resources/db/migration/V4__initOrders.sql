CREATE TABLE orders(
                       order_id bigint not null,
                       user_id bigint not null,
                       car_id bigint not null,
                       day bigint,
                       payment double precision,
                       driver boolean,
                       rejected boolean,
                       closed boolean,
                       created TIMESTAMP,
                       ended TIMESTAMP,
                       damage VARCHAR(255),
                       passport VARCHAR(50) not null,
                       PRIMARY KEY(order_id));

-- drop table orders;
CREATE TABLE user_orders(
                                order_id INT,
                                user_id INT,
                                car_id INT,
                                PRIMARY KEY(user_id, order_id),
                                FOREIGN KEY(user_id) REFERENCES users(id),
                                FOREIGN KEY(car_id) REFERENCES car(id)
);