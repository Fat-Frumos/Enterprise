create table invoice
(
    invoice_id bigint      not null
        primary key,
    order_id bigint      not null,
    user_id  bigint      not null,
    car_id   bigint      not null,
    payment  double precision,
    driver   boolean,
    damage   varchar(255)
);