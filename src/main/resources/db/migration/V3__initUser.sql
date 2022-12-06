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
    closed   boolean,
    salt     varchar(255),
    passport varchar(20),
    phone    varchar(20)
);

alter table users
    owner to Pasha;

INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3958, 'ronald@example.com', 'ronald', 'ronald', true, 'en', 'user', true, '2', 'ronald@example.com', 'ronald@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3963, 'travis@example.com', 'travis', 'travis', true, 'en', 'guest', true, '2', 'travis@example.com', 'travis@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3959, 'darlene@example.com', 'darlene', 'darlene', true, 'en', 'guest', null, '2', 'darlene@example.com', 'darlene@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3967, 'dennis@example.com', 'dennis', 'dennis', true, 'en', 'user', false, '2', 'dennis@example.com', 'dennis@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3961, 'daryl@example.com', 'daryl', 'daryl', true, 'en', 'guest', false, '2', 'daryl@example.com', 'daryl@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3960, 'gabriel@example.com', 'gabriel', 'gabriel', true, 'en', 'guest', false, '2', 'gabriel@example.com', 'gabriel@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3962, 'neil@example.com', 'neil', 'neil', true, 'en', 'user', true, '2', 'neil@example.com', 'neil@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3966, 'jessie@example.com', 'jessie', 'jessie', true, 'en', 'user', false, '2', 'jessie@example.com', 'jessie@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (16492, 'alice@i.ua', 'alice', '830231804ffd654cd4b4f85c585f01faf92f7c60490b92582386221ba1192d2a', true, 'ua', 'user', false, 'f32a4c36-13d7-42f5-b68e-42d28355389f', 'alice@i.ua', 'alice@i.ua');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (5, 'admin@i.ua', 'neo', '78f4e1d15894cdefe7ea659e0f3ea13543910934e9f2eff96f8eb5f983f3b28e', true, 'en', 'admin', false, 'c988d8fb-8563-45a0-98d0-ea9f8a468eff', 'admin@i.ua', 'admin@i.ua');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (3965, 'ida@example.com', 'ada', '65b65b4c104d86972c2471eac164ae675fbff5a52b35740091a638b9c7fde1f4', true, 'ua', 'manager', false, '37c0d0a5-e8d6-40e6-8ff5-b4408bef3a60', 'ida@example.com', 'ida@example.com');
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (17359, 'maya@i.ua', 'mia', '1c3a8ecb625271ed08e5d7eada12effa6fb2fe359c2506bb136f53ce10b82d6c', true, '', 'user', false, '25e2ab33-2f87-4917-a773-eaf73c8df36e', null, null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (17235, 'bob@i.ua', 'bob', '84bd65029358ac22f406084f1e1dd2f246ac1462a3a309c69d86ac75eeab3731', true, 'ua', 'user', false, '7c3c6653-b7f8-4a60-a3a7-1b2c95725b1b', null, null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (16706, 'darlene@example.com', 'dan', 'f7d8238ee4b57a4520a82118c39ae90615e5d425495938c4440619f1240db27f', true, '', 'user', false, 'b0717c76-b79d-47f7-baf1-366accad34f0', null, null);
INSERT INTO public.users (id, email, name, password, active, language, role, closed, salt, passport, phone) VALUES (18903, 'darlene@example.com', 'den', '6aa2307dcae14690d236ba76dfc77ce07eef0f6bee5aba12e99d4959c58038bb', true, 'en', 'user', false, 'a81589e3-0d1f-4863-8e6f-53ff81e29e86', null, null);
