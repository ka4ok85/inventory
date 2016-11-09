package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;

@ComponentScan
@Service
public class StorelocationService {

    @Autowired
    private StoreRepository storeRepository;
    
    @Autowired
    private StorelocationRepository storelocationRepository;

	public StorelocationService() {
	}

    public Iterable<Storelocation> getAllStorelocationsByStore(Long storeId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        return storelocationRepository.findByStore(storeId);
    }
    
    
    public Storelocation findStorelocation(Long storelocationId) {
    	Storelocation storelocation = storelocationRepository.findOne(storelocationId);
        if (storelocation == null) {
            throw new NotFoundException(storelocationId.toString());
        }
        
    	return storelocation;
    }
}
