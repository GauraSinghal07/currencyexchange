-- Create tables
CREATE TABLE IF NOT EXISTS items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);

-- Create table for bills
CREATE TABLE IF NOT EXISTS bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    customer_tenure INT NOT NULL,
    original_currency VARCHAR(255),
    target_currency VARCHAR(255)
);

-- Insert sample data into items
INSERT INTO items (name, category, price) VALUES ('Laptop', 'electronics', 1200);
INSERT INTO items (name, category, price) VALUES ('Apple', 'grocery', 2);
INSERT INTO items (name, category, price) VALUES ('Headphones', 'electronics', 150);
INSERT INTO items (name, category, price) VALUES ('Banana', 'grocery', 1);

-- Insert a sample bill
INSERT INTO bills (user_id, customer_tenure, original_currency, target_currency)
VALUES (1, 3, 'USD', 'EUR');  -- Assuming user_id is provided (change as needed)
