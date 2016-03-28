package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;
import com.example.entity.Productlocation;
import com.example.entity.Storelocation;

@Repository
public interface ProductlocationRepository extends CrudRepository<Productlocation, Long> {

    Productlocation findByProductAndStorelocation(Product product, Storelocation storelocation);
}

