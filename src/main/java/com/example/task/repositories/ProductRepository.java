package com.example.task.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import com.example.task.model.Product;

public interface ProductRepository extends CrudRepository<Product, ObjectId>{

}
