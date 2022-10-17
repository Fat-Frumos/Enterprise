###There is a list of Cars in the system, for which it is necessary to implement: 
+ choice by brand, price;
- choice according to quality class ? (expensive -> cheap) sorting by price();
+ sort by rental price;
+ sort by name. (UI)

####An unregistered customer cannot place an order.

####The customer: 
+ registers user in the system, (validate User) 
- chooses a car 
- makes a rental order.

####The order:
- In the order data the client indicates passport data, 
- option: with driver/without driver,  
- lease term (days).
- submit the price

####The system: 
- generates an Invoice, which the client pays 
- (send to email )

####The manager:
- reviews the order 
- may reject it, 
- giving a reason.
- also registers the return of the car, 
- in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights:
- adding, 
- deleting cars, 
- editing car information(upload);
- blocking / unblocking- the user;
- registration of managers in the system (role).

===============================

#####TODO: sign up user email

####INFO: FATAL: new field cost price
####INFO: FATAL: order
####INFO: FATAL: UI -role
#####TODO: Test 40%

#####!http://localhost:8080/views/ to http://localhost:8080/ 
####INFO: FATAL: too many connections for role "heroku"
//views/index.html (? static view index.jsp /)

#####TODO: save params, pagination &page into params
#####TODO: checkUser is active UI
#####TODO: validation fields
#####TODO: regex
#####TODO: Access denied(UI)
#####TODO edit/delete/ByName

#####TODO: @WebServlet(@WebServlet(urlPatterns = "/users") && urlPatterns = "/card")
#####TODO: Authentication, authorization, and accounting: /login/registration user login.jsp
#####TODO: PopUp && update cars
#####TODO: DataTag layer support
#####TODO: ConnectionFactory vs DBManager
#####TODO: transactional getConnection Factory, dao setConnection
#####TODO: @One to Many
#####TODO: User frontend in card storage
#####TODO: Click only image. Btn +/-
#####TODO: cars list randomize? input
#####TODO: listener
#####TODO: request scope
#####TODO: Token /session
#####TODO: web.xml file
#####TODO: Forget Password email
#####TODO: Sign up

#####TODO: PRG redirect
#####TODO boolean validateUser = userService.validateUser(name, password, user);
#####TODO boolean isValidUser = securityService.checkUser(name, password);

#####TODO http://localhost:8080/manager/text/deploy?war=file:/path/to/bar.war
#####TODO: package.json
conf\server.xml
- listener.xml
  //TODO boolean saved
- 
- At least one JAR was scanned for TLDs yet contained no TLDs. 
- Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. 
- Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
- A child container failed during start