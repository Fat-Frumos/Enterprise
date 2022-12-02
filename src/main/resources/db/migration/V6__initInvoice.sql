drop table invoices;
create table invoices
(
    invoice_id bigint      not null
        primary key,
    user_id  bigint,
    car_id   bigint,
    payment  double precision,
    damage   varchar(255),
    reason   varchar(255),
    passport varchar(50),
    phone varchar(50)
);