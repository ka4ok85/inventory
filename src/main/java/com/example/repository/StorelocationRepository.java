package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Store;
import com.example.entity.Storelocation;

@Repository
public interface StorelocationRepository extends CrudRepository<Storelocation, Long> {

    Storelocation findByStore(Store store);
    Storelocation findByStoreAndShelfAndSlot(Store store, Long shelf, Long slot);
}

