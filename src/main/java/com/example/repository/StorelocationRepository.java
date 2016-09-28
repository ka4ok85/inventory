package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;
import com.example.entity.Productlocation;
import com.example.entity.Storelocation;

@Repository
public interface StorelocationRepository extends CrudRepository<Storelocation, Long> {

	Iterable<Storelocation> findByStore(Long store);
    Storelocation findByStoreAndShelfAndSlot(Long store, Long shelf, Long slot);
}

