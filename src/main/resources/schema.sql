CREATE TABLE IF NOT EXISTS Drivers (
    id SMALLSERIAL NOT NULL primary key,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(15),
    user_id INT
); 

CREATE TABLE IF NOT EXISTS Orders (
    id SERIAL NOT NULL  Primary Key,
    order_number VARCHAR (20) NOT NULL,
    client_name VARCHAR(50) NOT NULL,
    client_address VARCHAR(1024) NOT NULL,
    order_status VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS Products (
    id BIGSERIAL NOT NULL Primary Key,
    model VARCHAR (256) NOT NULL
);

CREATE TABLE IF NOT EXISTS OrderItems (
    id BIGSERIAL NOT NULL Primary Key,
    order_id INT NOT NULL REFERENCES Orders (id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES Products (id)
);

CREATE TABLE IF NOT EXISTS Deliveries (
    id BIGSERIAL NOT NULL Primary Key,
    driver_id SMALLINT NOT NULL REFERENCES Drivers (id),
    order_id INT NOT NULL REFERENCES Orders (id),
    delivery_date DATE NOT NULL
);

