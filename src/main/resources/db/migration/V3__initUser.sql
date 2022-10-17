create table users
(
    id       bigint not null
        primary key,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    active   boolean,
    language varchar(10)
);

INSERT INTO public.users (id, email, name, password, active, language) VALUES (3964, 'amelia.kennedy58@example.com', 'amelia', 'amelia', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3965, 'ida.davis80@example.com', 'ida', 'ida', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3967, 'dennis.craig82@example.com', 'dennis', 'dennis', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3958, 'ronald.reynolds66@example.com', 'ronald', 'ronald', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3959, 'darlene.edwards15@example.com', 'darlene', 'darlene', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3960, 'gabriel.jackson91@example.com', 'joe', 'joe', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3961, 'daryl.bryant94@example.com', 'jack', 'jack', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3966, 'jessie.patterson68@example.com', 'jessie', 'jessie', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3963, 'travis.wright36@example.com', 'travis', 'travis', null, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3962, 'neil.parker43@example.com', 'neil', 'neil', false, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (1, 'admin@i.ua', 'admin', 'admin', true, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (3, 'user@i.ua', 'user', 'user', true, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (2, 'manager@i.ua', 'manager', 'manager', true, 'en');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (0, 'guest@i.ua', 'guest', 'guest', true, 'ua');
INSERT INTO public.users (id, email, name, password, active, language) VALUES (8169210100136365512, 'bob@i.ua', 'bob', 'bob', null, null);
