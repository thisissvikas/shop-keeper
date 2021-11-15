CREATE TABLE if not exists products(
    id int unsigned PRIMARY KEY AUTO_INCREMENT,
    name varchar(1000),
    price decimal(10, 2),
    category varchar(255),
    description text,
    specifications json,
    created_timestamp timestamp DEFAULT '0000-00-00 00:00:00',
    updated_timestamp timestamp DEFAULT '0000-00-00 00:00:00'
);


CREATE TABLE if not exists product_images(
    id int unsigned PRIMARY KEY AUTO_INCREMENT,
    product_id int unsigned,
<<<<<<< HEAD
    image longblob,
=======
    image blob,
    description text,
>>>>>>> ff7e157030458e11ba8d2fd06cb17a4696652641
    created_timestamp timestamp DEFAULT '0000-00-00 00:00:00',
    updated_timestamp timestamp DEFAULT '0000-00-00 00:00:00',
    FOREIGN KEY (product_id) REFERENCES products(id)
);
