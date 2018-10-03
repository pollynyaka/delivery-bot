insert into DRIVERS (NAME, PHONE_NUMBER, USER_ID) values ('Петров Иван' , '89834743214', 564251355);
insert into DRIVERS (NAME, PHONE_NUMBER, USER_ID) values ('Иванов Михаил' , '89893743214', 111111111);
insert into DRIVERS (NAME, PHONE_NUMBER, USER_ID) values ('Сидоров Евгений' , '896035331313', 222222222);

insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0001', 'Кукушкин', 'Москва, Скорняжный переулок, 3с2, кв 8', 'ACCEPTED');
insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0002', 'Петросян', 'Москва, улица Большая Ордынка, 16, кв 55', 'ACCEPTED');
insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0003', 'Говорухин', 'Москва, проспект Мира, 15, кв 32', 'ACCEPTED');
insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0004', 'Тараторкин', 'Москва, Утренняя улица, 3, кв 656', 'ACCEPTED');
insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0005', 'Кузнецов', 'Москва, улица Металлургов, 5, кв 23', 'COMPLETED');
insert into ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS) values ('or_0006', 'Сибиряков', 'Москва, Новогиреевская улица, 8к1, кв 3', 'ACCEPTED');

insert into PRODUCTS (MODEL) values ('Чайник Fissman "Sunflower", со свистком, 3 л');
insert into PRODUCTS (MODEL) values ('LEGO City Arctic Expedition Конструктор Передвижная арктическая база');
insert into PRODUCTS (MODEL) values ('Apple EarPods гарнитура 3,5 mm');
insert into PRODUCTS (MODEL) values ('Чехол для одежды "Paterra", 65 х 100 см, 3 шт');

insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (1, 1);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (1, 2);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (2, 3);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (3, 3);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (3, 4);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (4, 1);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (5, 2);
insert into ORDERITEMS (ORDER_ID, PRODUCT_ID) values (6, 3);

insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (1, 1, NOW());
insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (1, 2, NOW());
insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (1, 5, NOW());
insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (1, 6, NOW());
insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (2, 3, NOW());
insert into DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE) values (2, 4, NOW());

update DELIVERIES set DELIVERY_DATE = NOW();


