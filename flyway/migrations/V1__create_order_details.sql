-- V1__create_order_details.sql
CREATE TABLE order_details (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255),
    bike_model VARCHAR(50),
    quantity INT,
    order_date TIMESTAMP
);
