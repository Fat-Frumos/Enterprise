CREATE TABLE orders(
                       order_id bigint not null,
                       customer_id bigint not null,
                       car_id bigint not null,
                       day bigint not null,
                       payment double precision not null,
                       driver boolean,
                       rejected boolean,
                       closed boolean,
                       order_date TIMESTAMP,
                       damage VARCHAR(255),
                       PRIMARY KEY(order_id));

-- drop table orders;
CREATE TABLE customer_orders(
                                order_id INT,
                                customer_id INT,
                                car_id INT,
                                PRIMARY KEY(customer_id, order_id),
                                FOREIGN KEY(customer_id) REFERENCES users(id),
                                FOREIGN KEY(car_id) REFERENCES car(id)
);