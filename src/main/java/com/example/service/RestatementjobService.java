package com.example.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Restatementjob;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.entity.User;
import com.example.repository.ProductRepository;
import com.example.repository.RestatementjobRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;
import com.example.repository.UserRepository;
import com.example.wrappers.RestatementJobWrapperAdd;
import com.example.wrappers.RestatementjobWrapperFull;

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

    @Autowired
    private UserRepository userRepository;

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

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date blankDate = null;

        Restatementjob restatementjob = new Restatementjob();
        restatementjob.setProduct(product);
        restatementjob.setStore(store);
        restatementjob.setStorelocation(storelocation);
        restatementjob.setExpectedQuantity(expectedQuantity);

        restatementjob.setDateAdded(date);
        restatementjob.setDateProcessed(blankDate);
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

        Date date = new Date();
        restatementjob.setDateProcessed(date);
        restatementjob.setStatus(Restatementjob.STATUS_COMPLETE);
        restatementjobRepository.save(restatementjob);

        return restatementjob;
    }

    public Iterable<Restatementjob> getAll() {
        return restatementjobRepository.findAll();
    }

    public Iterable<Restatementjob> getAllByStoreAndUserId(Long storeId, Long userId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NotFoundException(userId.toString());
        }

        return restatementjobRepository.findByStoreAndUser(store, user);
    }

    public Restatementjob getById(Long id) {
        return restatementjobRepository.findOne(id);
    }

    
    
    
	public Restatementjob addJob(RestatementJobWrapperAdd restatementJobWrapperAdd) {

		Restatementjob restatementjob = new Restatementjob();
		Long productId = restatementJobWrapperAdd.getProductId();
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }		
		
        restatementjob.setProduct(product);

        Long storeId = restatementJobWrapperAdd.getStoreId();
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }        
        
        restatementjob.setStore(store);

        Long storeLocationId = restatementJobWrapperAdd.getStorelocationId();
        Storelocation storelocation = storelocationRepository.findOne(storeLocationId);
        if (storelocation == null) {
            throw new NotFoundException(storeLocationId.toString());
        }        
        
        restatementjob.setStorelocation(storelocation);
        restatementjob.setExpectedQuantity(restatementJobWrapperAdd.getExpectedQuantity());

        Date date = new Date();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date blankDate = null;
        restatementjob.setDateAdded(date);
        restatementjob.setDateProcessed(blankDate);
        restatementjob.setStatus(Restatementjob.STATUS_NEW);

        Long userId = restatementJobWrapperAdd.getUserId();
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NotFoundException(userId.toString());
        }

        restatementjob.setUser(user);		
		Restatementjob restatementjobAdded = restatementjobRepository.save(restatementjob);

		return restatementjobAdded;
	}
    
	public Restatementjob addJob(RestatementjobWrapperFull restatementjobWrapperFull) {

		Restatementjob restatementjob = new Restatementjob();
		Long productId = restatementjobWrapperFull.getProductId();
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }		
		
        restatementjob.setProduct(product);

        Long storeId = restatementjobWrapperFull.getStoreId();
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }        
        
        restatementjob.setStore(store);

        Long storeLocationId = restatementjobWrapperFull.getStorelocationId();
        Storelocation storelocation = storelocationRepository.findOne(storeLocationId);
        if (storelocation == null) {
            throw new NotFoundException(storeLocationId.toString());
        }        
        
        restatementjob.setStorelocation(storelocation);
        restatementjob.setExpectedQuantity(restatementjobWrapperFull.getExpectedQuantity());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date blankDate = null;
        restatementjob.setDateAdded(date);
        restatementjob.setDateProcessed(blankDate);
        restatementjob.setStatus(Restatementjob.STATUS_NEW);

        Long userId = restatementjobWrapperFull.getUserId();
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NotFoundException(userId.toString());
        }

        restatementjob.setUser(user);		
		Restatementjob restatementjobAdded = restatementjobRepository.save(restatementjob);

		return restatementjobAdded;
	}

}
