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
    card     varchar(50),
    term     timestamp
);

INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (44647261, 18974, 136312, 4125, null, null, null, '2022-11-01 00:04:19.902000', null, 'івоапівопфіво', '121231231231', '2022-11-05 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (105860518, 18974, 136312, 1650, null, null, null, '2022-11-01 00:09:47.277000', null, 'івоапівопфіво', '121231231231', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (104351528, 18974, 24, 2475, null, null, null, '2022-11-01 00:11:48.421000', null, 'івоапівопфіво', '121231231231', '2022-11-03 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (131812192, 18974, 9625145, 975, false, null, null, '2022-11-01 00:15:00.518000', null, 'івоапівопфіво', '121231231231', '2022-11-03 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (32522688, 18974, 9625145, 950, false, null, null, '2022-11-01 00:19:10.213000', null, 'івоапівопфіво', '121231231231', '2022-11-02 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (84101015, 18974, 84, 6100, true, null, null, '2022-11-01 00:24:57.987000', null, 'івоапівопфіво', '121231231231', '2022-11-04 00:00:00.000000');
INSERT INTO public.orders (order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, card, term) VALUES (69291939, 18974, 45664, 4500, false, null, null, '2022-11-01 00:25:14.119000', null, 'івоапівопфіво', '121231231231', '2022-11-03 00:00:00.000000');
