###There is a list of Cars in the system, for which it is necessary to implement: 
+ choice by brand(selection), price;
+ choice according to quality class ? (expensive -> cheap price) sorting by cost(input asc/desc);
+ sort by rental price;

+ An unregistered customer cannot place an order.

####The customer: 
+ registers user in the system, (validate User) 
+ chooses a car 
+ makes a rental order.

####The order:
+ In the order data the client indicates passport data, 
+ option: with driver/without driver,  
+ lease term (days).
+ submit the price


####The system: 
+ generates an Invoice, which the client pays Invoice + (itextpdf) 
+/- (send to email ) MimeMessage with the invoice pdf

####The manager:
+ reviews the order 
+ may reject it, 
+ giving a reason.
+ also registers the return of the car, 
+ in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights:
====car====
+ adding (upload), 
+deleting cars, 
+ editing car information;
# (<form action="/cars" method="post">)

====user====
+ blocking / unblocking- the user;
+ registration of managers in the system (role).

#####TODO: java doc Servlet, StaticServlet, CarsServlet, CartServlet, ..., Dao, UserDao

===============================
####INFO: FATAL: user try jdbc
####INFO: FATAL: Service I
####INFO: TEST: testcontainers
####TODO: Transaction isolation (read committed) setAutoCommit false, config file
####TODO: Duration
####TODO FATAL: delete from card
####TODO //String sql = String.format("UPDATE car SET rent = true WHERE id = %d", id);
####TODO FATAL: order to Dto invoice email
####INFO: FATAL: forgot email html(pdf)
####INFO: FATAL: private static final List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");
####INFO: FATAL: new dynamic price + cost
####INFO: FATAL: admin filter
####INFO: FATAL: role enum
####INFO: //TODO DELETE vs UPDATE
####INFO: //TODO 2. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies -
####INFO: with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.
####INFO:+ Destination 'src\main\webapp\WEB-INF\classes\templates\slider-007.png' already exists
####INFO: FATAL: cars update and delete Set
####INFO: redirect to http://localhost:8080/upload?slider-007.png
####INFO: FATAL: add car http://localhost:8080/order
#####TODO FATAL: userDto-enum
#####TODO FATAL: validate submit order failed
#####TODO: Auto-generated delete method
#####TODO: FATAL: Car edit delete
####INFO: FATAL: auth order/user authentication

####INFO: FATAL: JdbcCarTemplate:58 SELECT id, name, brand, model, path, price, cost, year FROM car LIMIT 80;
####INFO: TEST SERVLET: http://localhost:8080/
register?orderId=48384729&userId=16492&carId=87&passport=AA+123+456+789&term=2022-11-10+00%3A00%3A00.0&damage=Act+of+Nature&rejected=on&closed=on

web: java $JAVA_OPTS -jar target/webapp-runner.jar --port $PORT target/*.war
//upload img
#####TODO: @One to Many

validate html w3c curl

#####TODO: user save params
####INFO: check session
#####TODO: customer Role manger getOrders
#####TODO: customer Role
context xml screen

// TODO admin option  - > user.setRole()
// String sql = String.format("DELETE FROM car where car_id = ?", id);

throw new CarException
####INFO: FATAL: admin option

####INFO: FATAL: tag file/ internationalization

#####TODO: sign up user email
#####TODO: limit page
#####TODO: select customer, http://localhost:8080/cart?id=132121

####INFO: FATAL: new field cost price
####INFO: FATAL: date
####INFO: FATAL: UI -role
#####TODO FATAL:: Test 40%

####INFO: FATAL: post send redirect to customer
#####!http://localhost:8080/views/ to http://localhost:8080/ 

//views/index.html (? static view index.jsp /)

#####TODO: save params, pagination &page into params
#####TODO: checkUser is active UI
#####TODO: validation fields
#####TODO: regex
#####TODO: Access denied(UI)
#####TODO edit/delete/ByName

#####TODO: @WebServlet(@WebServlet(urlPatterns = "/users") && urlPatterns = "/cart")

#####TODO: Authentication, authorization, and accounting: /login/registration user login.jsp
#####TODO: PopUp && update cars
#####TODO: DataTag layer support

#####TODO: ConnectionFactory vs DBManager
#####TODO: transactional getConnection Factory, dao setConnection

#####TODO: User frontend in cart storage
#####TODO: Click only image. Btn +/-
#####TODO: cars list randomize? input
#####TODO: request scope
#####TODO: Token /session

#####TODO: PRG redirect
#####TODO boolean validateUser = userService.validateUser(name, password, user);
#####TODO boolean isValidUser = securityService.checkUser(name, password);

#####TODO http://localhost:8080/manager/text/deploy?war=file:/path/to/bar.war
#####TODO: package.json
#####TODO: itext
conf\server.xml
- listener.xml

* Client sends Http Request to Web Server
* Code in Web Server => Input:HttpRequest, Output: HttpResponse JEE with Servlets
* Web Server responds with Http Response

//Java Platform, Enterprise Edition (Java EE) JEE6
//Servlet is a Java programming language class
//used to extend the capabilities of servers
//that host applications accessed by means of
//a request-response programming model.

//1. extends javax.servlet.http.HttpServlet
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//3. doPost(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?

UPDATE users SET name = 'alice', role = user, active = false WHERE id =18974
