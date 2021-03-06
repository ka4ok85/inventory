package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Store;
import com.example.repository.ProductRepository;
import com.example.repository.ProductinstoreRepository;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class Productstore {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;
    
    @Autowired
    private ProductinstoreRepository productinstoreRepository;

    public Productstore() {
    }

    public Product scanProduct(Long productId, Long storeId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }
        
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Productinstore existedProductinstore = productinstoreRepository.findByProductAndStore(product.getId(), store.getId());
        if (existedProductinstore == null) {
        	Productinstore productinstore = new Productinstore(product.getId(), store.getId(), quantity);
            Set<Productinstore> productinstores = product.getProductinstores();
            productinstores.add(productinstore);
            product.setProductinstores(productinstores);
        } else {
            existedProductinstore.setQuantity(quantity + existedProductinstore.getQuantity());
        }

        productRepository.save(product);

        return product;
    }

    public Product sellProduct(Long productId, Long storeId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Productinstore existedProductinstore = productinstoreRepository.findByProductAndStore(product.getId(), store.getId());
        if (existedProductinstore == null) {
            throw new NotFoundException(productId.toString() + "-" + storeId.toString());
        } else {
            if (existedProductinstore.getQuantity() < quantity) {
            	System.out.println("ProductstoreService: Not enough product " + product.getName());
                throw new RuntimeException("Not enough product " + product.getName());
            } else {
                existedProductinstore.setQuantity(existedProductinstore.getQuantity() - quantity);
            }
        }

        productRepository.save(product);

        return product;
    }
    
    public Productinstore findProductinstore(Long productId, Long storeId) {
    	Productinstore productinstore = productinstoreRepository.findByProductAndStore(productId, storeId);
        if (productinstore == null) {
            throw new NotFoundException(productId.toString() + '_' + storeId.toString());
        }
        
    	return productinstore;
    }
}
