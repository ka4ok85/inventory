package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Storelocation;

@Repository
public interface StorelocationRepository extends CrudRepository<Storelocation, Long> {

    Storelocation findByStore(Long store);
    Storelocation findByStoreAndShelfAndSlot(Long store, Long shelf, Long slot);
}

