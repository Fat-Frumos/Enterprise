package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CarsTest {
//    private static final Cars INSTANCE = Cars.getInstance();
    Logger logger = LoggerFactory.getLogger(CarsTest.class);
    private static final List<Car> cars = new ArrayList<>();
    static final Car X7 = new Car.Builder().id(1l).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).year(2022).build();
    static final Car X5 = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).year(2020).build();
    JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    Service service = new CarService(mockCarDao);

    @BeforeEach
    void init() {
        cars.add(X5);
        cars.add(X7);
        service = new CarService(mockCarDao);
        service.addCar(X5);
        service.addCar(X7);
    }

    @Test
    @DisplayName(value = "Test-Mock Get Cars from db")
    void testGetCars() {
        List<Car> actual = service.getAll();
        assertEquals(cars, actual);
    }

    @Test
    @DisplayName(value = " Get Car")
    void testGetEmptyCar() throws NoSuchFieldException, IllegalAccessException {
        Car car = new Car();
        Class<? extends Car> clazz = car.getClass();
        Car carById = service.getById(1);
        assertEquals(null, car.getName());
        assertEquals(carById.getBrand(), "McLauren");
        assertEquals(carById.getName(), "SuperCar\n");

        Mockito.verify(service, Mockito.times(1)).getById(1);

//        for (Field field : clazz.getDeclaredFields()) {
//
//            System.out.println(field.getName());
//        }
        Field field = car.getClass().getDeclaredField("id");
        field.setAccessible(true);

        Object value = field.get(car);
        System.out.println(value);

//        for (Method method : clazz.getDeclaredMethods()) {
//            try {
//                method.invoke(null);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            } catch (InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(method);
//            System.out.println(car);
//        }


    }

    @Test
    @DisplayName(value = "Set Car name")
    void testSetBrandNewCar() {
        String brand = "BMW";
        Car car = new Car();
        car.setBrand(brand);
        assertEquals(brand, car.getName());
    }

    String uri = "/cars";

    @Test
    public void testFindByBrand() {
        Car car = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(10000.0).year(2020).build();
        when(service.getAll("BMW")).thenReturn(cars);

        logger.info(String.format("%s", service.getAll()));
        logger.info(String.format("%s", car));
        logger.info(String.format("%s", cars));

        assertEquals(2, car.getId());
        assertEquals(100000.0, car.getPrice());
        assertEquals("BMW", car.getBrand());
    }

    @Test
    public void testFindAllReturnCorrectData() {
        JdbcCarDao jdbcCarDao = new JdbcCarDao();
        List<Car> cars = jdbcCarDao.findAll();
        assertFalse(cars.isEmpty());

        for (Car car : cars) {
            assertNotEquals(0, car.getId());
            assertNotNull(car.getBrand());
            assertNotEquals(0, car.getPrice());
        }
    }

    @Test
    @DisplayName(value = "Test Find All Cars")
    void testFindAllCars() throws Exception {

    }

    @Test
    @DisplayName(value = "Test Find Cars By Brand Id Length")
    void testFindCarsByBrandIdSize() throws Exception {
        String brand = "BMW";
//        List<Car> cars = mockCarDao.findAll(brand);

        when(mockCarDao.findAll(brand)).thenReturn(List.of(X5, X7));
        assertEquals(2, service.getAll().size());

    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Asc Founded Cars")
    void testSortedByRatingAscFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Desc Founded Cars")
    void testSortedByRatingDescFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Sorted By Price Asc Founded Cars")
    void testSortedByPriceAscFoundedCars() throws Exception {

    }

    @Test
    @DisplayName(value = "Test Sorted By Price Desc Founded Cars")
    void testSortedByPriceDescFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Find Car By Id")
    void testFindCarById() throws Exception {

    }

    @Test
    void getAll() {
        when(mockCarDao.findAll()).thenReturn(List.of(X5, X7));
        assertEquals(2, service.getAll().size());
    }

    @Test
    void findById() {
        when(mockCarDao.findById(X7.getId())).thenReturn(Optional.of(X7));
        assertEquals("X7", service.getById(X7.getId()).getName());
    }

    @Test
    void save() {
        when(mockCarDao.save(X5)).thenReturn(true);
        assertTrue(service.addCar(X5));
    }

    @Test
    void delete() {
        when(mockCarDao.delete(X5.getId())).thenReturn(true);
        assertTrue(service.delete(X5.getId()));
    }

}
