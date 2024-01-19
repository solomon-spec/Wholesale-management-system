CREATE DATABASE javawms;
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender ENUM('Male', 'Female') NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_admin TINYINT(1) DEFAULT 0
);

CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(255) NOT NULL UNIQUE,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    QuantityInStock INT NOT NULL,
    DateAdded TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastUpdated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `Order` (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    User_ID INT,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    PaymentStatus ENUM('Pending', 'Completed') DEFAULT 'Pending',
    ShippingAddress VARCHAR(255),
    CONSTRAINT FK_User_Order FOREIGN KEY (user_ID) REFERENCES User(user_ID)
);

CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT FK_Order_OrderItem FOREIGN KEY (OrderID) REFERENCES Order(OrderID),
    CONSTRAINT FK_Product_OrderItem FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

CREATE TABLE Cart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    CONSTRAINT FK_User_Cart FOREIGN KEY (UserID) REFERENCES User(UserID),
    CONSTRAINT FK_Product_Cart FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);