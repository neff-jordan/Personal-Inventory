CREATE DATABASE inventory;
CREATE TABLE products(
    id BIGSERIAL PRIMARY KEY NOT NULL, 
    name text NOT NULL, 
    type text NOT NULL, 
    location text NOT NULL, 
    quantity integer NOT NULL
);
 
--add whatever objects you want here using either SQL directly or Java. 
