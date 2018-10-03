INSERT INTO DRIVERS (NAME, PHONE_NUMBER, USER_ID)
select * from (
	SELECT 'Петров Иван', '89834743214', 564251355 union all
	SELECT 'Иванов Михаил', '89893743214', 111111111 union all
	SELECT 'Сидоров Евгений', '896035331313', 222222222) AS s
WHERE NOT EXISTS (SELECT * FROM DRIVERS);

INSERT INTO ORDERS (ORDER_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, ORDER_STATUS)
select * from (
	SELECT 'or_0001', 'Кукушкин', 'Москва, Скорняжный переулок, 3с2, кв 8', 'ACCEPTED' union all
	SELECT 'or_0002', 'Петросян', 'Москва, улица Большая Ордынка, 16, кв 55', 'ACCEPTED' union all
	SELECT 'or_0003', 'Говорухин', 'Москва, проспект Мира, 15, кв 32', 'ACCEPTED' union all
	SELECT 'or_0004', 'Тараторкин', 'Москва, Утренняя улица, 3, кв 656', 'ACCEPTED' union all
	SELECT 'or_0005', 'Кузнецов', 'Москва, улица Металлургов, 5, кв 23', 'COMPLETED' union all
	SELECT 'or_0006', 'Сибиряков', 'Москва, Новогиреевская улица, 8к1, кв 3', 'ACCEPTED') AS s
WHERE NOT EXISTS (SELECT * FROM ORDERS);

INSERT INTO PRODUCTS (MODEL)
select * from (
	SELECT 'Чайник Fissman "Sunflower", со свистком, 3 л' union all
	SELECT 'LEGO City Arctic Expedition Конструктор Передвижная арктическая база' union all
	SELECT 'Apple EarPods гарнитура 3,5 mm' union all
	SELECT 'Чехол для одежды "Paterra", 65 х 100 см, 3 шт') AS s
WHERE NOT EXISTS (SELECT * FROM PRODUCTS);

INSERT INTO ORDERITEMS (ORDER_ID, PRODUCT_ID)
select * from (
	SELECT 1, 1 union all
	SELECT 1, 2 union all
	SELECT 2, 3 union all
	SELECT 3, 3 union all
	SELECT 3, 4 union all
	SELECT 4, 1 union all
	SELECT 5, 2 union all
	SELECT 6, 3) AS s
WHERE NOT EXISTS (SELECT * FROM ORDERITEMS);

INSERT INTO DELIVERIES (DRIVER_ID, ORDER_ID, DELIVERY_DATE)
select * from (
	SELECT 1, 1, NOW() union all
	SELECT 1, 2, NOW() union all
	SELECT 1, 5, NOW() union all
	SELECT 1, 6, NOW() union all
	SELECT 2, 3, NOW() union all
	SELECT 2, 4, NOW()) AS s
WHERE NOT EXISTS (SELECT * FROM DELIVERIES);

update DELIVERIES set DELIVERY_DATE = NOW();


