-- LOGIN ORDERS
insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2001,sysdate(), 'login', 'cheese, bacon, chilli souce', 100, '');

insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2002,sysdate(), 'login', 'cheese, bacon, chilli souce', 200, '');

insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2003,sysdate(), 'login', 'cheese, bacon, chilli souce', 300, '');

-- TOM ORDERS
insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2004,'2019-11-21', 'tom', 'cheese, bacon, chilli souce', 300, '');

insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2005,'2019-10-22', 'tom', 'cheese, bacon, chilli souce', 300, '');

-- CAT ORDERS
insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2006,'2018-11-22', 'Cat', 'cheese, bacon, chilli souce', 300, '');

insert into Restaurant_order(id, date, waiter_name, items,  price, notes)
values (2007,'2018-11-22', 'Cat', 'cheese, bacon, chilli souce', 300, '');

-- PIZZAS
insert into Pizza (id, name, ingredients, price, order_id)
values (7001,'HOT ROAD PIZZA','mozzarella, pepperoni, carmel onion, jalapeño', 25, 2004);

insert into Pizza (id, name, ingredients, price, order_id)
values (7002,'BBQ PIZZA','BBQ sauce, mozzarella, bacon, smocked chicken, carmel onion', 30,2004);

insert into Pizza (id, name, ingredients, price, order_id)
values (70023,'ITALIAN PIZZA','tomato sauce, mozzarella, carpaccio, oregano, corregio', 30,2004);
