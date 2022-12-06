create table invoices
(
    invoice_id bigint not null
        primary key,
    user_id    bigint,
    car_id     bigint,
    payment    double precision,
    damage     varchar(255),
    reason     varchar(255),
    passport   varchar(50),
    phone      varchar(50)
);

alter table invoices
    owner to Pasha;

INSERT INTO public.invoices (invoice_id, user_id, car_id, payment, damage, reason, passport, phone) VALUES (6374303, 3965, 233, 1840, 'Struck another car', 'Missing an insurance renewal', 'RA 123 456 789', ' 38 (050) 5714549');
INSERT INTO public.invoices (invoice_id, user_id, car_id, payment, damage, reason, passport, phone) VALUES (4540024, 16492, 30, 2900, 'Struck another car', 'Damages arising due to a Car Accident', 'AA 123 456 789', ' 123456789123');
INSERT INTO public.invoices (invoice_id, user_id, car_id, payment, damage, reason, passport, phone) VALUES (5064728, 16492, 33, 5500, 'Struck another car', ' Third Party Car Insurance', 'RA 123 456 789', ' 123456789123');
