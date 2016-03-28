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
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class Productstore {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

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

        Productinstore existedProductinstore = null;
        for (Productinstore productinstore : product.getProductinstores()) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                existedProductinstore = productinstore;
                break;
            }
        }

        if (existedProductinstore == null) {
            Productinstore productinstore = new Productinstore(product, store, quantity);
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

        Productinstore existedProductinstore = null;
        for (Productinstore productinstore : product.getProductinstores()) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                existedProductinstore = productinstore;
                break;
            }
        }

        if (existedProductinstore == null) {
            throw new NotFoundException(productId.toString() + "-" + storeId.toString());
        } else {
            if (existedProductinstore.getQuantity() < quantity) {
                throw new NotFoundException("Not enough " + productId.toString());
            } else {
                existedProductinstore.setQuantity(existedProductinstore.getQuantity() - quantity);
            }
        }

        productRepository.save(product);

        return product;
    }
}
