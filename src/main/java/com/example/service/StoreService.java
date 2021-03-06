package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productlocation;
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
    
    public Store findStore(Long storeId) {
    	Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        return store;
    }
}
