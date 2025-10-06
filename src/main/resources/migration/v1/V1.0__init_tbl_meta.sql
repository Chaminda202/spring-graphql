CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    stock INTEGER NOT NULL
);

-- inset product table
INSERT INTO product (name, category, price, stock) VALUES ('Laptop','Electronics',1250.50, 110);
INSERT INTO product (name, category, price, stock) VALUES ('Smart Phone','Electronics',650.50, 50);
INSERT INTO product (name, category, price, stock) VALUES ('Tab','Electronics',550.50, 140);
INSERT INTO product (name, category, price, stock) VALUES ('Office Chair','Furniture',150.60, 70);
INSERT INTO product (name, category, price, stock) VALUES ('Office Table','Furniture',250.50, 140);
INSERT INTO product (name, category, price, stock) VALUES ('Note Book','Stationary',10.50, 560);
