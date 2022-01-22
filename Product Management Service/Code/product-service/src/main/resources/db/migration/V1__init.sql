CREATE TABLE if not exists products(
    id int unsigned PRIMARY KEY AUTO_INCREMENT,
    name varchar(1000),
    price decimal(10, 2),
    category varchar(255),
    description text,
    specifications json,
    created_timestamp timestamp,
    updated_timestamp timestamp
);


CREATE TABLE if not exists product_images(
    id int unsigned PRIMARY KEY AUTO_INCREMENT,
    product_id int unsigned,
    image longblob,
    created_timestamp timestamp,
    updated_timestamp timestamp,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
