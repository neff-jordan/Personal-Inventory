CREATE DATABASE inventory;
CREATE TABLE products(
    id BIGSERIAL PRIMARY KEY NOT NULL, 
    names text NOT NULL, 
    type text NOT NULL, 
    location text NOT NULL, 
    quantity integer NUT NULL
);
 
--add whatever objects you want here using either SQL directly or Java. 