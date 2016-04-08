package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Productlocation;

@Repository
public interface ProductlocationRepository extends CrudRepository<Productlocation, Long> {

    Productlocation findByProductAndStorelocation(Long product, Long storelocation);
    Productlocation findByProductAndStore(Long product, Long store);

}

