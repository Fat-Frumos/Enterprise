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
    term     timestamp,
    reason   varchar(255)
);

alter table orders
    owner to xhhiprtyikbzhv;

INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (70863071, 3965, 227, 600, true, true, true, '2022-11-02 23:09:39.789000', 'Skratch', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-04 00:00:00.000000', 'Driving without a License');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (4604773, 3965, 6458932, 12250, false, true, false, '2022-11-04 23:50:33.325000', 'T-Bone Accidents', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-11 00:00:00.000000', 'Driving a modified car without intimation');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (67783723, 16492, 221, 1100, true, true, true, '2022-11-06 12:34:56.432000', 'Crashed', 'RA 123 456 789', '+123456789123', '2022-11-06 00:00:00.000000', 'When you drink and drive');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (120605632, 3965, 227, 300, true, false, false, '2022-11-02 22:55:32.976000', 'Another car stuck me', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000', 'Damages arising due to a Car Accident ');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (30099696, 3965, 233, 1840, true, true, false, '2022-11-04 23:51:54.064000', 'Struck another car', 'RA 123 456 789', '+38 (050) 5714549', '2022-11-11 00:00:00.000000', 'Missing an insurance renewal');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (58542762, 3965, 6458932, 2450, true, true, false, '2022-11-04 23:49:51.194000', 'Act of Nature', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-11 00:00:00.000000', 'Natural disasters like floods, cyclones');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (411206, 3965, 546887, 850, true, true, false, '2022-11-03 01:30:22.288000', 'Struck another car', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000', ' Third Party Car Insurance');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (56247700, 16492, 221, 11000, true, true, true, '2022-11-06 12:39:13.332000', 'Act of Nature', 'AA 123 456 789', '+123456789123', '2022-11-16 00:00:00.000000', 'no payment');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (100942360, 3965, 72, 1550, true, false, true, '2022-11-03 01:37:17.731000', 'T-Bone Accidents', 'AA 123 456 789', '+38 (050) 5714549', '2022-11-02 00:00:00.000000', 'Driving without a Driver’s License');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason) VALUES (14763883, 17888, 6458932, 300, false, null, null, '2022-11-06 14:30:52.475000', null, 'AA 123 456 789', '+123456789123', '2022-11-06 00:00:00.000000', null);
