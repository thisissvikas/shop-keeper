CREATE TABLE products(
    id int PRIMARY KEY ,
    name varchar(255),
    price decimal(10,2),
    category varchar(255),
    description text,
    specifications json,
    created_timestamp timestamp,
    updated_timestamp timestamp
);


CREATE TABLE product_images(
    id int PRIMARY KEY,
    product_id int,
    image blob,
    description text,
    FOREIGN KEY (product_id) REFERENCES products(id)
);