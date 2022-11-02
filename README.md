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
- reviews the order 
- may reject it, 
- giving a reason.
- also registers the return of the car, 
- in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights:
====car====
- adding, 
- deleting cars, 
- editing car information(upload);
====user====
+- blocking / unblocking- the user;
- registration of managers in the system (role).

===============================
####INFO: FATAL: OrderServlet:80 Car null
####INFO: FATAL: forgot
####INFO: FATAL: JdbcCarTemplate:58 SELECT id, name, brand, model, path, price, cost, year FROM car LIMIT 80;

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

####INFO: FATAL: add car to db

service: log. err(msg, cause)
connection rollback
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
#####TODO: listener
#####TODO: request scope
#####TODO: Token /session
#####TODO: web.xml file

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


//        if (isValid) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user.getName());
//            session.setMaxInactiveInterval(30*60);
//            Cookie userName = new Cookie("user", user.getName());
//            userName.setMaxAge(30*60);
//            response.addCookie(userName);
//            List<Car> cars = carService.getRandom();
//            response.sendRedirect("/WEB-INF/views/main.jsp");
//        request.setAttribute("cars", userService.getRandom());
//        } else {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            request.setAttribute("errorMessage", "Invalid Credentials");
//            dispatcher.include(request, response);
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
//        }

//            HttpSession session = request.getSession();
//            session.setAttribute("user", user.getName());
//            session.setMaxInactiveInterval(30*60);
//
//            Cookie userName = new Cookie("user", user.getName());
//            userName.setMaxAge(30*60);
//            response.addCookie(userName);
//            List<Car> cars = carService.getRandom();
//            response.sendRedirect("/WEB-INF/views/main.jsp");
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

//    public UserDto toDto(User user) {
//        return new UserDto(
//                user.getName(),
//                user.getPassword(),
//                user.getEmail(),
//                Role.USER);
//    }

2022-10-18 16:36:33 INFO  CarException:8 Vehicle not found
at com.enterprise.rental.service.CarService.lambda$getById$0(CarService.java:43)
at com.enterprise.rental.service.CarService.getById(CarService.java:42)
at com.enterprise.rental.controller.CartServlet.doGet(CardServlet.java:49)