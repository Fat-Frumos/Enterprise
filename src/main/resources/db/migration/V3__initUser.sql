create table users
(
    id       bigint not null
        primary key,
    email    varchar(255),
    name varchar(255),
    password varchar(255)
);

INSERT INTO public.users (id, email, name, password) VALUES (123, 'admin@i.ua', 'admin', 'admin');
INSERT INTO public.users (id, email, name, password) VALUES (321, 'manager@i.ua', 'manager', 'manager');
INSERT INTO public.users (id, email, name, password) VALUES (111, 'user@i.ua', 'user', 'user');
INSERT INTO public.users (id, email, name, password) VALUES (3960, 'gabriel.jackson91@example.com', 'joe', 'joe');
INSERT INTO public.users (id, email, name, password) VALUES (3958, 'ronald.reynolds66@example.com', 'ronald', 'ronald');
INSERT INTO public.users (id, email, name, password) VALUES (3964, 'amelia.kennedy58@example.com', 'amelia', 'amelia');
INSERT INTO public.users (id, email, name, password) VALUES (3965, 'ida.davis80@example.com', 'ida', 'ida');
INSERT INTO public.users (id, email, name, password) VALUES (3962, 'neil.parker43@example.com', 'neil', 'neil');
INSERT INTO public.users (id, email, name, password) VALUES (3966, 'jessie.patterson68@example.com', 'jessie', 'jessie');
INSERT INTO public.users (id, email, name, password) VALUES (3961, 'daryl.bryant94@example.com', 'jack', 'jack');
