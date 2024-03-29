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
    cost  double precision,
    rent  boolean
);

alter table car
    owner to lnycichw;