package com.example.ProductSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // always need this tag on the database
public interface ProductDB extends JpaRepository<Product, Integer> {

    // lets the user delete a product from the database based off the name of the product
    long deleteByName(String name);
}


