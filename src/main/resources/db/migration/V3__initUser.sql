create table users
(
    id       bigint not null
        primary key,
    email    varchar(100),
    name     varchar(100),
    password varchar(25),
    active   boolean,
    language varchar(10),
    role     varchar(50)
);

INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3960, 'gabriel.jackson91@example.com', 'gabriel', 'gabriel', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3961, 'daryl.bryant94@example.com', 'daryl', 'daryl', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3964, 'amelia.kennedy58@example.com', 'kennedy', 'amelia', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3965, 'ida.davis80@example.com', 'ida', 'ida', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3967, 'dennis.craig82@example.com', 'dennis', 'dennis', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3958, 'ronald.reynolds66@example.com', 'ronald', 'ronald', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3959, 'darlene.edwards15@example.com', 'darlene', 'darlene', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (1, 'admin@i.ua', 'neo', 'neo', true, 'en', 'admin');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3966, 'jessie.patterson68@example.com', 'jessie', 'jessie', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3963, 'travis.wright36@example.com', 'travis', 'travis', null, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3962, 'neil.parker43@example.com', 'neil', 'neil', false, 'en', null);
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (18974, 'alice@i.ua', 'alice', 'alice', true, null, 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (16439, 'admin@i.ua', 'admin', 'admin', true, 'ua', 'admin');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (20434, 'bob@i.ua', 'bob', 'bob', true, null, 'user');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (2, 'manager@i.ua', 'manager', 'manager', true, 'en', 'manager');
INSERT INTO public.users (id, email, name, password, active, language, role) VALUES (3, 'user@i.ua', 'user', 'user', false, 'en', 'user');
