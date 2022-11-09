create table users
(
    id       bigint not null
        primary key,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    active   boolean,
    language varchar(10),
    role     varchar(50),
    closed   boolean
);

alter table users
    owner to Pasha;

INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (16492, 'alice@i.ua', 'alice', 'alice', true, null, 'user', false);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3964, 'amelia@example.com', 'kennedy', 'amelia', true, 'en', 'guest', true);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3962, 'neil@example.com', 'neil', 'neil', true, 'en', 'user', true);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (17888, 'Nathan@yesenia.net', 'bob', 'bob', true, 'en', 'user', false);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (5, 'admin@i.ua', 'neo', 'neo', true, 'en', 'admin', null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3959, 'darlene@example.com', 'darlene', 'darlene', true, 'en', 'guest', null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3967, 'dennis@example.com', 'dennis', 'dennis', false, 'en', 'user', null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3958, 'ronald@example.com', 'ronald', 'ronald', false, 'en', 'user', null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3960, 'gabriel@example.com', 'gabriel', 'gabriel', false, 'en', 'guest', false);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3963, 'travis@example.com', 'travis', 'travis', true, 'en', 'guest', false);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3965, 'ida@example.com', 'ada', 'ada', true, 'en', 'manager', null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3961, 'daryl@example.com', 'daryl', 'daryl', false, 'en', 'guest', true);
INSERT INTO public.users (id, email, name, password, active, language, role, closed) VALUES (3966, 'jessie@example.com', 'jessie', 'jessie', true, 'en', 'user', false);
