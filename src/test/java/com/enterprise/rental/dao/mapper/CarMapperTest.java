package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarMapperTest {
    private final CarMapper mapper = new CarMapper();

    @Test
    void testMapRow() throws SQLException {


        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(1l);
        when(resultSet.getString("name")).thenReturn("X7");
        when(resultSet.getString("brand")).thenReturn("BMW");
        when(resultSet.getString("model")).thenReturn("G07");
        when(resultSet.getString("path")).thenReturn("http//");
        when(resultSet.getDouble("price")).thenReturn(500.0);
        when(resultSet.getDouble("cost")).thenReturn(25000.0);
        when(resultSet.getString("year")).thenReturn("2022");

        Car car = mapper.mapRow(resultSet);

        assertEquals(1l, car.getId());
        assertEquals("BMW", car.getBrand());
        assertEquals("X7", car.getName());
        assertEquals("G07", car.getModel());
        assertEquals("http//", car.getPath());
        assertEquals(500.0, car.getPrice());
        assertEquals(25000.0, car.getCost());
        assertEquals(2022, car.getYear());

    }

    @Test
    void testMapRowRequest() throws SQLException {

        HttpServletRequest request = mock(HttpServletRequest.class);

        String[] fields = {"orderId", "carId", "userId", "passport", "reason", "phone", "damage", "payment", "created"};

        Map<String, String> params = new HashMap<>();
        for (String key : fields) {
            if (!"".equals(request.getParameter(key))
                    && request.getParameter(key) != null) {
                params.put(key, request.getParameter(key));
            }
        }
        System.out.println(params);

        long orderId = params.get(fields[0]) != null
                ? Long.parseLong(params.get(fields[0]))
                : UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;

//        long carId = Long.parseLong(params.get(fields[1]));
//        long userId = Long.parseLong(params.get(fields[2]));

        String passport = params.get(fields[3]);
        String reason = params.get(fields[4]);
        String phone = params.get(fields[5]);
        String damage = params.get(fields[6]);
        String payment = params.get(fields[7]);
        String createTimestamp = params.get(fields[8]);
        String timestamp = request.getParameter("term");

        if (timestamp.length() == 10) {
            timestamp += " 00:00:00.0";
        }

        Timestamp term = Timestamp.valueOf(timestamp);

        boolean closed = request.getParameter("closed") != null;
        boolean rejected = request.getParameter("rejected") != null;
        boolean driver = request.getParameter("driver") != null;

        double pay = payment != null
                ? Double.parseDouble(payment)
                : 0;

        Timestamp created = createTimestamp == null
                ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(createTimestamp);


        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("X7");
        when(request.getParameter("brand")).thenReturn("BMW");
        when(request.getParameter("model")).thenReturn("G07");
        when(request.getParameter("path")).thenReturn("http//");
        when(request.getParameter("price")).thenReturn("500.0");
        when(request.getParameter("cost")).thenReturn("25000.0");
        when(request.getParameter("year")).thenReturn("2022");

        Car car = mapper.carMapper(request);

        assertEquals(1l, car.getId());
        assertEquals("BMW", car.getBrand());
        assertEquals("X7", car.getName());
        assertEquals("G07", car.getModel());
        assertEquals("http//", car.getPath());
        assertEquals(500.0, car.getPrice());
        assertEquals(25000.0, car.getCost());
        assertEquals(2022, car.getYear());

    }

}