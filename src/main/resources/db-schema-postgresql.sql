CREATE SCHEMA IF NOT EXISTS BOT AUTHORIZATION postgres;

CREATE TABLE BOT.Drivers (
    id SMALLSERIAL NOT NULL primary key,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(15),
    user_id INT
);

CREATE TABLE BOT.Orders (
    id SERIAL NOT NULL  Primary Key,
    order_number VARCHAR (20) NOT NULL,
    client_name VARCHAR(50) NOT NULL,
    client_address VARCHAR(1024) NOT NULL,
    order_status VARCHAR(15) NOT NULL
);

CREATE TABLE BOT.Products (
    id BIGSERIAL NOT NULL Primary Key,
    model VARCHAR (256) NOT NULL
);

CREATE TABLE BOT.OrderItems (
    id BIGSERIAL NOT NULL Primary Key,
    order_id INT NOT NULL,
    product_id BIGINT NOT NULL
);

ALTER TABLE BOT.OrderItems
   ADD CONSTRAINT OrderItems_OrderId_FK Foreign Key
      (order_id) REFERENCES BOT.Orders (id);
ALTER TABLE BOT.OrderItems
   ADD CONSTRAINT OrderItems_ProductId_FK Foreign Key
      (product_id) REFERENCES BOT.Products (id);

CREATE TABLE BOT.Deliveries (
    id BIGSERIAL NOT NULL Primary Key,
    driver_id SMALLINT NOT NULL,
    order_id INT NOT NULL,
    delivery_date DATE NOT NULL
);
ALTER TABLE BOT.Deliveries
   ADD CONSTRAINT Deliveries_DriverId_FK Foreign Key
      (driver_id) REFERENCES BOT.Drivers (id);
ALTER TABLE BOT.Deliveries
   ADD CONSTRAINT Deliveries_OrderId_FK Foreign Key
      (order_id) REFERENCES BOT.Orders (id);