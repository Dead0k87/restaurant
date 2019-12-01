-- LOGIN ORDERS
insert into Restaurant_order(id, date, waiter_name,  price, notes)
values (2001,sysdate(), 'login', 100, '');

insert into Restaurant_order(id, date, waiter_name,   price, notes)
values (2002,sysdate(), 'login', 200, '');

insert into Restaurant_order(id, date, waiter_name, price, notes)
values (2003,sysdate(), 'login', 300, '');

-- TOM ORDERS
insert into Restaurant_order(id, date, waiter_name, price, notes)
values (2004,'2019-11-21', 'tom', 300, '');

insert into Restaurant_order(id, date, waiter_name,  price, notes)
values (2005,'2019-10-22',  'tom', 300, '');

-- CAT ORDERS
insert into Restaurant_order(id, date, waiter_name,  price, notes)
values (2006,'2018-11-22', 'Cat', 300, '');

insert into Restaurant_order(id, date, waiter_name,   price, notes)
values (2007,'2018-11-22', 'Cat', 300, '');

-- PIZZAS
insert into Menu_Item (id, name, description,item_type, price, order_id)
values (7001,'HOT ROAD PIZZA','mozzarella, pepperoni, carmel onion, jalapeño', 'PIZZA', 25, 2004);

insert into Menu_Item (id, name, description,item_type , price, order_id)
values (7002,'BBQ PIZZA','BBQ sauce, mozzarella, bacon, smocked chicken, carmel onion','PIZZA', 30,2004);

insert into Menu_Item (id, name, description, item_type, price, order_id)
values (7003,'ITALIAN PIZZA','tomato sauce, mozzarella, carpaccio, oregano, corregio', 'PIZZA', 30,2004);
