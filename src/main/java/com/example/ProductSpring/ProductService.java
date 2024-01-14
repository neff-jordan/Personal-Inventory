package com.example.ProductSpring;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Need on every service layer
public class ProductService {

    @Autowired // ???
    ProductDB db;

    public void addProduct(Product p) {
        db.save(p);
    }

    public List<Product> getAllProducts(){
        return db.findAll();
    }

    public Product getProduct(String name) {
        for(Product p : db.findAll()) {
            if (p.getName().equals(name)) return p;
        }
        return null;
    }

    public Product getProduct(Product name) {
        for(Product p : db.findAll()) {
            if (p.getName().equals(name)) return p;
        }
        return null;
    }

    public String findProduct(String name) {
        for(Product p : db.findAll()) {
            if (name.equals(p))
                return name;
        }
        return name;
    }

    @Transactional // needed for the ProductDB to delete
    public void deleteProduct(String name) {
        db.deleteByName(name);
    }

    public void updateProduct(Product existingProduct) {
        db.save(existingProduct);
    }
}
