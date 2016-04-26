package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Restatementjob;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.repository.ProductRepository;
import com.example.repository.RestatementjobRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;

@ComponentScan
@Service
public class RestatementjobService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StorelocationRepository storelocationRepository;

    @Autowired
    private RestatementjobRepository restatementjobRepository;

    public RestatementjobService() {
    }

    public Restatementjob addJob(Long productId, Long storeId, Long storeLocationId, Long expectedQuantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Storelocation storelocation = storelocationRepository.findOne(storeLocationId);
        if (storelocation == null) {
            throw new NotFoundException(storeLocationId.toString());
        }

        Restatementjob restatementjob = new Restatementjob();
        restatementjob.setProduct(productId);
        //restatementjob.setStore(storeId);
        restatementjob.setStore(store);
        restatementjob.setStorelocation(storeLocationId);
        restatementjob.setExpectedQuantity(expectedQuantity);
        restatementjob.setStatus(Restatementjob.STATUS_NEW);

        restatementjobRepository.save(restatementjob);

        return restatementjob;
    }

    public Restatementjob completeJob(Long restatementjobId) {
        Restatementjob restatementjob = restatementjobRepository.findOne(restatementjobId);
        if (restatementjob == null) {
            throw new NotFoundException(restatementjobId.toString());
        }

        if (restatementjob.getStatus().equals(Restatementjob.STATUS_NEW) == false) {
            throw new NotFoundException("Restatementjob is already completed");
        }

        restatementjob.setStatus(Restatementjob.STATUS_COMPLETE);
        restatementjobRepository.save(restatementjob);

        return restatementjob;
    }

    public Iterable<Restatementjob> getAll() {
        return restatementjobRepository.findAll();
    }

    public Restatementjob getById(Long id) {
        return restatementjobRepository.findOne(id);
    }

}
