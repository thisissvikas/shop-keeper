CREATE TABLE products (
  id int PRIMARY KEY,
  name varchar(255),
  price decimal(10,2),
  category varchar(255),
  image blob,
  description text,
  specification JSON,
  created_on timestamp,
  last_updated timestamp 
);
