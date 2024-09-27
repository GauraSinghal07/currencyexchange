-- Create tables
CREATE TABLE IF NOT EXISTS items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_type VARCHAR(255) NOT NULL,
    customer_tenure INT NOT NULL,
    original_currency VARCHAR(10) NOT NULL,
    target_currency VARCHAR(10) NOT NULL
);

-- Insert sample data into items
INSERT INTO items (name, category, price) VALUES ('Laptop', 'electronics', 1200);
INSERT INTO items (name, category, price) VALUES ('Apple', 'grocery', 2);
INSERT INTO items (name, category, price) VALUES ('Headphones', 'electronics', 150);
INSERT INTO items (name, category, price) VALUES ('Banana', 'grocery', 1);

-- Insert a sample bill
INSERT INTO bills (user_type, customer_tenure, original_currency, target_currency)
VALUES ('employee', 3, 'USD', 'EUR');
