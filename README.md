###There is a list of Cars in the system, for which it is necessary to implement: 
+ choice by brand, price;
- choice according to quality class ? (expensive -> cheap) sorting by price();
- sort by rental price;
- sort by name. (UI)

The customer registers in the system, chooses a car and makes a rental order.
- An  unregistered customer cannot place an order. 
- In the order data the client indicates passport data, option &#39;with driver&#39; / &#39;without driver&#39;, lease term. 
- The system generates an Invoice, which the client pays.
- The manager reviews the order and may reject it, giving a reason. 
- The manager also registers the return of the car, in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights:
- adding, deleting cars, editing car information(upload);
- blocking / unblocking- the user;
- registration of managers in the system.

===============================
####INFO: FATAL: too many connections for role "xhhiprtyikbzhv"

UI
//view/index.html (? static view index.jsp /)
//view/22/22.jpg (popup)
//22/22.jsp (popup)
#####TODO: pagination &page=
#####TODO: navigator
#####TODO: validation fields
#####TODO: Autocomplete
#####TODO: Access denied(UI)

#####TODO: @WebServlet(@WebServlet(urlPatterns = "/user") && urlPatterns = "/card")
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
#####TODO: Forget Password/Sign up

#####TODO: PRG redirect

#####TODO http://localhost:8080/manager/text/deploy?war=file:/path/to/bar.war

conf\server.xml
- listener.xml

- At least one JAR was scanned for TLDs yet contained no TLDs. 
- Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. 
- Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
- A child container failed during start