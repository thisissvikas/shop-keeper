CREATE TABLE products (
  id int PRIMARY KEY,
  name varchar(255),
  price decimal(10,2),
  category varchar(255) FOREIGN KEY REFERENCES category(product_category)
);

CREATE TABLE product_details (
  id int PRIMARY KEY,
  image blob,
  description text
);

CREATE TABLE product_category (
  category varchar PRIMARY KEY,
  specification JSON 
);