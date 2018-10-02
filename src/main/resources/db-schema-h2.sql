CREATE SCHEMA IF NOT EXISTS BOT;
SET SCHEMA BOT;

CREATE TABLE Drivers (
    id SMALLINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(15),
    user_id INT
);
ALTER TABLE Drivers ADD CONSTRAINT Drivers_PK Primary Key (id);


CREATE TABLE Orders (
    id INT NOT NULL AUTO_INCREMENT,
    order_number VARCHAR (20) NOT NULL,
    client_name VARCHAR(50) NOT NULL,
    client_address VARCHAR(1024) NOT NULL,
    order_status VARCHAR(15) NOT NULL
);
ALTER TABLE Orders ADD CONSTRAINT Orders_PK Primary Key (id);


CREATE TABLE Products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    model VARCHAR (256) NOT NULL
);
ALTER TABLE Products ADD CONSTRAINT Products_PK Primary Key (id);


CREATE TABLE OrderItems (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id BIGINT NOT NULL
);
ALTER TABLE OrderItems ADD CONSTRAINT OrderItems_PK Primary Key (id);
ALTER TABLE OrderItems
	ADD CONSTRAINT OrderItems_OrderId_FK Foreign Key
		(order_id) REFERENCES Orders (id);
ALTER TABLE OrderItems
	ADD CONSTRAINT OrderItems_ProductId_FK Foreign Key
		(product_id) REFERENCES Products (id);


CREATE TABLE Deliveries (
    id BIGINT NOT NULL AUTO_INCREMENT,
    driver_id SMALLINT NOT NULL,
    order_id INT NOT NULL,
    delivery_date DATE NOT NULL
);
ALTER TABLE Deliveries ADD CONSTRAINT Deliveries_PK Primary Key (id);
ALTER TABLE Deliveries
	ADD CONSTRAINT Deliveries_DriverId_FK Foreign Key
		(driver_id) REFERENCES Drivers (id);
ALTER TABLE Deliveries
	ADD CONSTRAINT Deliveries_OrderId_FK Foreign Key
		(order_id) REFERENCES Orders (id);
