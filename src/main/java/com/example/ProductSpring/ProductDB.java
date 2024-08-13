package com.example.ProductSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // always need this tag on the database
public interface ProductDB extends JpaRepository<Product, Integer> {

    /**
     * Deletes a product from the database based on its name.
     * This method is automatically implemented by Spring Data JPA.
     *
     * @param name the name of the product to delete
     * @return the number of products deleted (should be 0 or 1)
     */
    long deleteByName(String name);
}


