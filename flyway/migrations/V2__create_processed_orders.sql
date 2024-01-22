-- V2__create_processed_orders.sql
CREATE TABLE processed_orders (
    processed_order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    processing_date TIMESTAMP,
    delivery_status VARCHAR(20)
);
