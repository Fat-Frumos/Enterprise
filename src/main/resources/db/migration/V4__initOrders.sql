create table orders
(
    order_id bigint      not null
        primary key,
    user_id  bigint      not null,
    car_id   bigint      not null,
    payment  double precision,
    driver   boolean,
    rejected boolean,
    closed   boolean,
    created  timestamp,
    damage   varchar(255),
    passport varchar(50) not null,
    phone    varchar(50),
    term     timestamp
);

alter table orders
    owner to xhhiprtyikbzhv;

INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (133844677, 5, 233, 2103.2, true, null, null, '2022-11-04 13:09:04.346000', null, 'KA 123 456 789', '+38 (050) 5714549', '2022-11-12 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (82395834, 3965, 227, 2700, true, false, true, '2022-11-03 11:55:39.161000', 'Struck another car', 'fdgfdghfd', '+38 (050) 5714549', '2022-11-12 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (59392024, 3965, 227, 250, false, true, true, '2022-11-02 23:40:11.993000', 'Crashed', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (70863071, 3965, 227, 600, true, true, true, '2022-11-02 23:09:39.789000', 'Skratch', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-04 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (411206, 3965, 546887, 850, true, false, false, '2022-11-03 01:30:22.288000', 'Struck another car', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (100942360, 3965, 72, 1550, true, false, false, '2022-11-03 01:37:17.731000', 'T-Bone Accidents', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (120605632, 3965, 227, 300, true, false, true, '2022-11-02 22:55:32.976000', 'Another car stuck me', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (62737235, 16492, 227, 2400, true, null, null, '2022-11-04 12:46:59.162000', null, 'KA 123 456 789', '+38 (050) 5714549', '2022-11-12 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (48384729, 16492, 87, 8050, true, false, true, '2022-11-03 19:32:32.171000', 'Act of Nature', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-10 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (95571413, 16492, 229, 1700, true, true, true, '2022-11-03 11:35:36.789000', 'T-Bone Accidents', 'RA 123 456 789', '+38 (050) 5714549', '2022-11-05 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (32787156, 16492, 229, 2550, true, false, true, '2022-11-02 23:36:29.025000', 'Painted', 'KA 123 456 789', '+38 (050) 5714549', '2022-11-05 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (89870198, 16492, 227, 2400, true, null, null, '2022-11-04 11:49:00.516000', null, 'RA 123 456 789', '+38 (050) 5714549', '2022-11-12 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term) VALUES (59787082, 16492, 231, 300, true, true, true, '2022-11-03 02:06:49.502000', 'Crashed', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-05 00:00:00.000000');
