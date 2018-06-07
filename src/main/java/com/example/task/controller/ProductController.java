package com.example.task.controller;


import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.model.Product;
import com.example.task.repositories.ProductRepository;

@RestController
public class ProductController {
	

	@Autowired
    ProductRepository productRepository;

    @GetMapping(value="/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    }

    @PostMapping(value="/products")
    public ObjectId save(@RequestBody Product product) {
        productRepository.save(product);

        return product.getId();
    }

    @GetMapping(value="/products/{id}")
    public Product show(@PathVariable ObjectId id) {
         Optional<Product> product = productRepository.findById(id);
         return product.get();
    }

    @PutMapping(value="/products/{id}")
    public Product update(@PathVariable ObjectId id, @RequestBody Product product) {
        Product prod = productRepository.findById(id).get();        
            prod.setProdName(product.getProdName());        
            prod.setProdDesc(product.getProdDesc());       
            prod.setProdPrice(product.getProdPrice());
            prod.setProdImage(product.getProdImage());
        productRepository.save(prod);
        return prod;
    }

    @DeleteMapping(value="/products/{id}")
    public String delete(@PathVariable ObjectId id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return "product deleted";
    }
    
}
