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
+ generates an Invoice, which the client pays Invoice + (i text pdf) 
+/- (send to email ) MimeMessage with the invoice pdf

####The manager role:
====order/edit====
+ reviews the order 
+ may reject it, 
+ giving a reason.
+ also registers the return of the car,

====invoice====
+ in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights role admin
====car/edit====
+ adding (upload image), 
+ deleting cars, 
+ editing car information;

====user/edit====
+ blocking / unblocking- the user;
+ registration of managers in the system (role).
