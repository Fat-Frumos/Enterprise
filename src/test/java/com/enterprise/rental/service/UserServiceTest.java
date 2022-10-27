//package com.enterprise.rental.service;
//
//import com.enterprise.rental.dao.UserDao;
//import com.enterprise.rental.dao.mapper.UserMapper;
//import com.enterprise.rental.entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//
////TODO mock
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserService userService;
//    private UserDao userDao;
//    @Spy
//    private UserMapper userMapper;
//
//
//    @BeforeEach
//    public void setUp() {
//        userService = new UserService(userDao, userMapper);
//    }
//
//    @Test
//    public void testFindById() {
//        int id = 1;
//        User user = getUser().setId(id).setOrders(new ArrayList<>());
//
//        when(userDao.getById(id)).thenReturn(Optional.of(user));
//
//        Optional<UserDTO> userDTO = userService.getById(id);
//
//        verify(userDao).getById(id);
//        verify(userMapper).toDTO(user);
//
//        verifyNoMoreInteractions(userDao);
//        verifyNoMoreInteractions(userMapper);
//
//        assertTrue(userDTO.isPresent());
//        assertThat(userDTO.get(), is(equalTo(expectedUser)));
//    }
//
//
//}
//
////
////    /**
////     * This test checks, if there are bijection between all cars
////     * And set of cars among all users
////     */
////    @Test
////    public void carsUserServiceTest() {
////        CarService carService = new CarService();
////        List<Car> cars = carService.getAll();
////
////        UserService userService = new UserService();
////        List<User> users = userService.getAll();
////        int size = 0;
//////        for (User user : users) {
//////            user.setCars(userService.findAllCarsForUser(user));
//////            size += user.getCars().size();
//////            for (Car s : user.getCars()) {
//////                assertTrue(cars.contains(s));
//////            }
//////        }
////        assertEquals(size, cars.size());
////    }
////
////    /**
////     * This test checks, if there are between all cars
////     * And set of cars among all users
////     */
//////    @Test
//////    public void carUserServiceTest() {
//////        CarService carService = new CarService();
//////        List<Car> cars = carService.getAll();
//////
//////        UserService userService = new UserService();
//////        List<User> users = userService.getAll();
//////        int size = 0;
//////        for (User u : users) {
//////            u.setCars(userService.findAllCarsForUser(u));
//////            size += u.getCars().size();
//////            for (Car s : u.getCars()) {
//////                assertTrue(cars.contains(s));
//////            }
//////        }
//////
//////        assertEquals(size, cars.size());
//////    }
////    @Test
////    public void userServiceTestNotNull() {
////        UserService service = new UserService();
////        List<User> users = service.getAll();
////        for (User user : users) {
////            assertNotNull(user);
////        }
////    }
//////            assertEquals(user., service.findByName(user.getName()));
////
////    @Test
////    public void userServiceTestByName() {
////        UserService service = new UserService();
////        List<User> users = service.getAll();
////
////        for (User user : users) {
////            assertEquals(service.getById(user.getUserId()), service.findByName(user.getName()));
////        }
////    }
////
//////    @Test
//////    public void carServiceTest() {
//////        CarService service = new CarService();
//////        List<Car> cars = service.getAll();
//////        for (Car s : cars) {
//////            assertEquals(s, service.getById(s.getId()));
//////        }
//////    }
////
////
////}