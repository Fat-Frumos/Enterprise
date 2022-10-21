package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//TODO mock
class UserServiceTest {

    /**
     * This test checks, if there are bijection between all cars
     * And set of cars among all users
     */
    @Test
    public void carsUserServiceTest() {
        CarService carService = new CarService();
        List<Car> cars = carService.getAll();

        UserService userService = new UserService();
        List<User> users = userService.getAll();
        int size = 0;
//        for (User user : users) {
//            user.setCars(userService.findAllCarsForUser(user));
//            size += user.getCars().size();
//            for (Car s : user.getCars()) {
//                assertTrue(cars.contains(s));
//            }
//        }
        assertEquals(size, cars.size());
    }

    /**
     * This test checks, if there are between all cars
     * And set of cars among all users
     */
//    @Test
//    public void carUserServiceTest() {
//        CarService carService = new CarService();
//        List<Car> cars = carService.getAll();
//
//        UserService userService = new UserService();
//        List<User> users = userService.getAll();
//        int size = 0;
//        for (User u : users) {
//            u.setCars(userService.findAllCarsForUser(u));
//            size += u.getCars().size();
//            for (Car s : u.getCars()) {
//                assertTrue(cars.contains(s));
//            }
//        }
//
//        assertEquals(size, cars.size());
//    }
    @Test
    public void userServiceTestNotNull() {
        UserService service = new UserService();
        List<User> users = service.getAll();
        for (User user : users) {
            assertNotNull(user);
        }
    }
//            assertEquals(user., service.findByName(user.getName()));

    @Test
    public void userServiceTestByName() {
        UserService service = new UserService();
        List<User> users = service.getAll();

        for (User user : users) {
            assertEquals(service.getById(user.getUserId()), service.findByName(user.getName()));
        }
    }

//    @Test
//    public void carServiceTest() {
//        CarService service = new CarService();
//        List<Car> cars = service.getAll();
//        for (Car s : cars) {
//            assertEquals(s, service.getById(s.getId()));
//        }
//    }
}