package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.entity.Store;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public StoreService() {
    }

    public Iterable<Store> getAll() {
        return storeRepository.findAll();
    }
}
