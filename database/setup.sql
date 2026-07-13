
CREATE DATABASE IF NOT EXISTS restaurant_db;
USE restaurant_db;

-- 1. Categories
CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Menu Items
CREATE TABLE IF NOT EXISTS menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    status ENUM('Available', 'Unavailable') DEFAULT 'Available',
    category_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- 3. Customers
CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    loyalty_points INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Staff
CREATE TABLE IF NOT EXISTS staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    role VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    salary DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Orders
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    staff_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Completed', 'Cancelled') DEFAULT 'Pending',
    total DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL
);

-- 6. Order Items
CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES menu_items(id) ON DELETE CASCADE
);

-- 7. Payments
CREATE TABLE IF NOT EXISTS payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method ENUM('Cash', 'Card', 'Online') DEFAULT 'Cash',
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);


INSERT INTO categories (name, description) VALUES
('Appetizers', 'Starters and small bites'),
('Main Course', 'Main dishes and entrees'),
('Desserts', 'Sweet treats and desserts'),
('Beverages', 'Drinks and refreshments');

INSERT INTO menu_items (name, price, description, category_id, status) VALUES
('Spring Rolls', 5.99, 'Crispy vegetable spring rolls', 1, 'Available'),
('Garlic Bread', 4.99, 'Toasted bread with garlic butter', 1, 'Available'),
('Chicken Curry', 12.99, 'Spicy chicken curry with rice', 2, 'Available'),
('Grilled Salmon', 15.99, 'Grilled salmon with vegetables', 2, 'Available'),
('Beef Steak', 18.99, 'Grilled beef steak with mashed potatoes', 2, 'Available'),
('Chocolate Cake', 6.99, 'Rich chocolate layer cake', 3, 'Available'),
('Ice Cream', 4.99, 'Vanilla ice cream with toppings', 3, 'Available'),
('Fresh Juice', 3.99, 'Freshly squeezed orange juice', 4, 'Available'),
('Coffee', 2.99, 'Hot brewed coffee', 4, 'Available'),
('Green Tea', 2.49, 'Japanese green tea', 4, 'Available');

INSERT INTO customers (name, phone, email, address) VALUES
('John Doe', '0771234567', 'john@email.com', 'Colombo'),
('Jane Smith', '0772345678', 'jane@email.com', 'Kandy'),
('Bob Wilson', '0773456789', 'bob@email.com', 'Galle');

INSERT INTO staff (name, role, phone, email, salary) VALUES
('Alice Manager', 'Manager', '0761111111', 'alice@restaurant.com', 50000.00),
('Bob Waiter', 'Waiter', '0762222222', 'bob@restaurant.com', 30000.00),
('Charlie Chef', 'Chef', '0763333333', 'charlie@restaurant.com', 45000.00);
