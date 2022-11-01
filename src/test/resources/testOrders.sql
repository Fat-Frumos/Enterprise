create table car
(
    id    bigserial
        primary key,
    name  text             not null,
    brand text             not null,
    model text             not null,
    price double precision not null,
    year  integer          not null,
    path  text             not null,
    date  timestamp,
    cost  double precision
);

create table users
(
    id       bigint not null
        primary key,
    email    varchar(100),
    name     varchar(100),
    password varchar(25),
    active   boolean,
    language varchar(10),
    role     varchar(50)
);

create table orders
(
    order_id bigint      not null
        primary key,
    user_id  bigint      not null,
    car_id   bigint      not null,
    day      bigint,
    payment  double precision,
    driver   boolean,
    rejected boolean,
    closed   boolean,
    created  timestamp,
    card    varchar(50),
    damage   varchar(255),
    passport varchar(50) not null
);

CREATE TABLE user_orders(
                            order_id INT,
                            user_id INT,
                            car_id INT,
                            PRIMARY KEY(user_id, order_id),
                            FOREIGN KEY(user_id) REFERENCES users(id),
                            FOREIGN KEY(car_id) REFERENCES car(id)
);

alter table car
    owner to pasha;

INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (221, 'Force', 'BMW', 'F430', 1050, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/0.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (231, 'McLaren', 'Lamborghini', 'GT-7', 100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/10.png', '2022-08-28 23:34:17.000000', 24000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (232, 'AeroCar', 'Ferrari', 'F420', 999.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/11.png', '2022-08-28 23:34:17.000000', 82000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (233, 'Camaro', 'Ferrari', 'ST1', 212.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/12.png', '2022-08-28 23:34:17.000000', 38000);

INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (1, 'neo@i.ua', 'neo', 'neo', true, 'en', 'admin');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (18974, 'alice@i.ua', 'alice', 'alice', true, null, 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (16439, 'admin@i.ua', 'admin', 'admin', true, 'ua', 'admin');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (20434, 'bob@i.ua', 'bob', 'bob', true, null, 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (2, 'manager@i.ua', 'manager', 'manager', true, 'en', 'manager');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3, 'user@i.ua', 'user', 'user', false, 'en', 'user');

INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (56050078, 18974, 22, null, null, null, null, null, '2022-10-25 02:32:59.249000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (58935240, 18974, 22, null, null, null, null, null, '2022-10-25 02:34:50.221000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (70338475, 18974, 36, null, null, null, null, null, '2022-10-25 02:35:06.304000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (37047144, 18974, 227, null, null, null, null, null, '2022-10-25 13:50:01.322000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (30033222, 18974, 9625145, null, null, null, null, null, '2022-10-25 13:53:11.512000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (16662958, 18974, 227, null, null, null, null, null, '2022-10-25 13:55:19.760000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (100093823, 18974, 87, null, null, null, null, null, '2022-10-25 14:00:06.431000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (17712464, 18974, 72, null, null, null, null, null, '2022-10-25 14:51:29.018000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (87835712, 18974, 10, null, null, null, null, null, '2022-10-25 15:06:26.794000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (44846079, 18974, 227, null, null, null, null, null, '2022-10-25 15:06:55.399000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (113921376, 18974, 227, null, null, null, null, null, '2022-10-25 15:08:43.687000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (110841518, 18974, 136312, null, null, null, null, null, '2022-10-25 15:09:07.977000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (111756443, 18974, 227, null, null, null, null, null, '2022-10-25 15:48:45.649000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (62079905, 18974, 227, null, null, null, null, null, '2022-10-25 15:50:09.524000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (31608396, 18974, 224, null, null, null, null, null, '2022-10-25 23:23:46.011000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (67652314, 18974, 546464, null, null, null, null, null, '2022-10-25 23:30:58.841000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (127815426, 18974, 546464, null, null, null, null, null, '2022-10-25 23:32:36.868000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (103370044, 18974, 227, null, null, null, null, null, '2022-10-25 23:45:02.021000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (68044099, 18974, 220, null, null, null, null, null, '2022-10-25 23:46:22.183000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (81153141, 18974, 220, null, null, null, null, null, '2022-10-25 23:47:07.755000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (27935327, 18974, 220, null, null, null, null, null, '2022-10-25 23:48:37.166000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (55790320, 18974, 227, null, null, null, null, null, '2022-10-25 23:52:20.628000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (3165882, 18974, 226, null, null, null, null, null, '2022-10-25 23:52:41.875000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (98323826, 18974, 0, null, null, null, null, null, '2022-10-25 23:52:55.907000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (75516948, 1, 227, null, null, null, null, null, '2022-10-27 01:54:18.184000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (27020675, 18974, 220, null, null, null, null, null, '2022-10-27 02:11:06.768000', null, null, 'AA 123 456 789');
INSERT INTO public.orders (order_id, user_id, car_id, day, payment, driver, rejected, closed, created, card, damage, passport) VALUES (127877549, 18974, 227, null, null, null, null, null, '2022-10-27 02:13:44.827000', null, null, 'AA 123 456 789');
