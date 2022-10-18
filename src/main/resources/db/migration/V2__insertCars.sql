create table car
(
    id    bigserial
        primary key,
    name  text             not null,
    brand text             not null,
    model text             not null,
    price double precision not null,
    cost  double precision not null,
    year  integer          not null,
    path  text             not null,
    date  timestamp
);

alter table car
    owner to xhhiprtyikbzhv;

INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (12654, 'Nemesis', 'Ferrari', '123GT', 999.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-23.jpg', null, 20000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (136312, 'admin', 'BMW', 'X5', 800, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-21.jpg', '2022-10-10 10:51:12.930266', 20000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (132121, 'SLR', 'Lamborghini', 'Benz', 1100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-22.jpg', null, 20000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (10, 'Camaro', 'Bugatti', 'GT-7', 202.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-2.jpg', '2022-08-29 23:34:10.000000', 20000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (30, 'AeroCar', 'Lamborghini', 'GTO', 1500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-30.jpg', '2022-08-28 23:34:17.000000', 20000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (987456, 'FlyCar', 'Xpeng', 'ST1', 1100, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-1.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (6458932, 'Spider', 'Ferrari', 'F430', 300, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-25.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (9625145, 'Camaro', 'Koenigsegg', 'GT-7', 750, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-24.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (546464, 'Corsa', 'Maserati', 'MC12', 200, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-28.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (465413, 'AeroCar', 'Xpeng', 'Inc', 1000, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-0.jpg', '2022-08-29 23:34:17.000000', 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (546887, 'Cayenne', 'Porsche', 'Turbo GT', 800, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-27.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (468484, 'Shelby', 'Ford', 'Mustang', 400, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-26.jpg', null, 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (230, 'AeroCar', 'Koenigsegg', 'Inc', 212.9, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-10.jpg', '2022-08-29 23:34:17.000000', 25000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (654654, 'admin', 'Xpeng', 'Hammer', 250, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-31.jpg', '2022-10-13 17:46:52.317608', 30000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (35, 'FlyCar', 'Lamborghini', 'GTO', 150, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-35.jpg', '2022-08-28 23:34:17.000000', 30000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (34, 'FlyCar', 'BMW', 'GTO', 750, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-34.jpg', '2022-08-28 23:34:17.000000', 30000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (32, 'AeroCar', 'Bugatti', 'Veyron', 150, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-32.jpg', '2022-08-28 23:34:17.000000', 30000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (33, 'AeroCar', 'Porsche', 'GTO', 500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-33.jpg', '2022-08-28 23:34:17.000000', 30000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (4654, 'FlyCar', 'Porsche', 'XX', 1000, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-3.jpg', '2022-08-29 23:34:17.000000', 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (38, 'AeroCar', 'Koenigsegg', 'CC8', 800, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-38.jpg', '2022-08-28 23:34:17.000000', 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (36, 'FlyCar', 'Ferrari', 'GTO', 200, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-36.jpg', '2022-08-28 23:34:17.000000', 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (45664, 'FlyCar', 'Xpeng', 'ST1', 200, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-51.jpg', null, 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (1231321, 'AeroCar', 'Maserati', 'X8', 300, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-4.jpg', '2022-08-29 23:34:17.000000', 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (37, 'AeroCar', 'Koenigsegg', 'CC8', 1100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-37.jpg', '2022-08-28 23:34:17.000000', 40000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (16, 'SLR', 'Lamborghini', 'GT', 300, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-52.jpg', null, 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (7987, 'AeroCar', 'Xpeng', 'Inc', 212.9, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-9.jpg', '2022-08-29 23:34:17.000000', 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (19, 'Camaro', 'BMW', 'X7', 250, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-54.jpg', null, 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (20, 'Force', 'Ferrari', 'F420', 212.9, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-55.jpg', null, 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (1264, 'Intetior', 'Ford', 'Next', 500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-8.jpg', '2022-08-29 23:34:17.000000', 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (21, 'Shelby', 'Ford', 'Mustang', 400, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-56.jpg', null, 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (17, 'Dino', 'Ferrari', '246GT', 300, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-53.jpg', null, 50000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (22, 'Cayenne', 'Porsche', 'Turbo GT', 500, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-57.jpg', null, 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (4569731, 'FlyCar', 'Xpeng', 'Inc', 202.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-5.jpg', '2022-08-29 23:34:17.000000', 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (1, 'SuperCar', 'Maserati', 'X8', 300, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-6.jpg', '2022-08-29 23:34:17.000000', 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (23, 'Corsa', 'Maserati', 'Turbo GT', 202.9, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-58.jpg', null, 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (24, 'Camaro', 'Bugatti', 'GT-7', 300, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-59.jpg', '2022-08-29 23:34:10.000000', 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (70, 'Nemesis', 'Ferrari', 'GTO', 1200, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-70.jpg', '2022-08-28 23:34:17.000000', 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (71, 'AeroCar', 'Ferrari', 'GTO', 1500, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-71.jpg', '2022-08-28 23:34:17.000000', 60000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (73, 'AeroCar', 'Koenigsegg', 'Koenigsegg', 1500, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-73.jpg', '2022-08-28 23:34:17.000000', 70000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (74, 'Nemesis', 'Porsche', 'Porsche', 1500, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-74.jpg', '2022-08-28 23:34:17.000000', 70000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (72, 'FlyCar', 'Koenigsegg', 'Veyron', 1500, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-72.jpg', '2022-08-28 23:34:17.000000', 70000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (77, 'Camaro', 'Lamborghini', 'CC8', 1000, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-77.jpg', '2022-08-28 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (6564321, 'Exterior', 'Ford', 'AM-4', 750, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-7.jpg', '2022-08-29 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (79, 'Tron', 'BMW', 'Next', 1200, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-79.jpg', null, 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (82, 'Tron', 'Lamborghini', 'GTO', 1500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-82.jpg', '2022-08-28 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (81, 'Huracan', 'Lamborghini', 'GTO', 250, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-81.jpg', '2022-08-28 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (76, 'Dino', 'Xpeng', 'Xpeng', 1000, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-76.jpg', '2022-08-28 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (75, 'SLR', 'Maserati', 'Maserati', 1200, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-75.jpg', '2022-08-28 23:34:17.000000', 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (78, 'Force', 'Ferrari', 'CC8', 1000, 2020, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-78.jpg', null, 75000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (86, 'Huracan', 'Lamborghini', 'GTO', 212.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-86.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (84, 'FlyCar', 'Porsche', 'GTO', 1500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-84.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (85, 'FlyCar', 'Ferrari', 'GTO', 1500, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-85.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (88, 'AeroCar', 'Koenigsegg', 'CC8', 100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-88.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (87, 'FlyCar', 'BMW', 'GTO', 1100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-87.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (83, 'Nemesis', 'Bugatti', 'Veyron', 1000, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-83.jpg', '2022-08-28 23:34:17.000000', 80000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (227, 'FlyCar', 'BMW', 'MC12', 250, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/1.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (223, 'AeroCar', 'Bugatti', 'Mustang', 202.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/7.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (226, 'Huracan', 'Ferrari', 'GTO', 300, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/8.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (224, 'AeroCar', 'Lamborghini', '123GT', 750, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/6.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (220, 'Camaro', 'Bugatti', 'ST1', 212.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/5.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (228, 'AeroCar', 'Maserati', 'Mustang', 999.9, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/4.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (229, 'Dino', 'BMW', 'ST1', 800, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/2.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (221, 'Force', 'BMW', 'F430', 1100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/0.png', '2022-08-28 23:34:17.000000', 100000);
INSERT INTO public.car (id, name, brand, model, price, year, path, date, cost) VALUES (225, 'AeroCar', 'Maserati', 'GT-7', 100, 2022, 'https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/3.png', '2022-08-28 23:34:17.000000', 100000);
