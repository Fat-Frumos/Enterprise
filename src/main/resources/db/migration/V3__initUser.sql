create table users
(
    id       bigint not null
        primary key,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    active   boolean,
    language varchar(10),
    role     varchar(50)
);

INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3964, 'amelia.kennedy58@example.com', 'amelia', 'amelia', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3965, 'ida.davis80@example.com', 'ida', 'ida', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3967, 'dennis.craig82@example.com', 'dennis', 'dennis', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3958, 'ronald.reynolds66@example.com', 'ronald', 'ronald', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3959, 'darlene.edwards15@example.com', 'darlene', 'darlene', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3960, 'gabriel.jackson91@example.com', 'joe', 'joe', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3961, 'daryl.bryant94@example.com', 'jack', 'jack', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3966, 'jessie.patterson68@example.com', 'jessie', 'jessie', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3963, 'travis.wright36@example.com', 'travis', 'travis', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3962, 'neil.parker43@example.com', 'neil', 'neil', false, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (0, 'guest@i.ua', 'guest', 'guest', true, 'ua', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3, 'user@i.ua', 'user', 'user', true, 'en', 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (1, 'admin@i.ua', 'admin', 'admin', true, 'en', 'admin');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (2986657635961949905, 'qwerty@gmail.com', 'bob', 'bob', null, null, 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (2, 'manager@i.ua', 'manager', 'manager', true, 'en', 'manager');
