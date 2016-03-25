package com.example.service;

import java.util.HashSet;
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

        Productinstore existedProductinstore = product.getSingleProductinstore(store);
        if (existedProductinstore == null) {
            System.out.println("NEW Productinstore");

            product.addToStore(store, quantity);
        } else {
            System.out.println("Exist " + existedProductinstore.toString());
            Long newQuantity = existedProductinstore.getQuantity() + quantity;
            System.out.println("newQuantity " + newQuantity);
/*
System.out.println("prod " + product.getProductinstores().size());
System.out.println("store " + store.getProductinstores().size());
            product.removeFromStoreAddress(store, existedProductinstore.getQuantity());
System.out.println("prod " + product.getProductinstores().size());
System.out.println("store " + store.getProductinstores().size());
            product.addToStore(store, newQuantity);
System.out.println("prod " + product.getProductinstores().size());
System.out.println("store " + store.getProductinstores().size());
*/
// opt 2
//product.getSingleProductinstore(store).setQuantity(2);

// opt 3
			//existedProductinstore.setQuantity(2);
			//System.out.println("quantity set ");
			//productinstoreRepository.save(existedProductinstore);
			//existedProductinstore.
			//System.out.println("saved ");
			
// opt 4
			existedProductinstore.setQuantity(new Long(2));
			System.out.println("quantity set ");
			Set<Productinstore> ps = new HashSet<>();
			ps.add(existedProductinstore);
			product.setProductinstores(ps);
			System.out.println("prod " + product.getProductinstores().size());
			//existedProductinstore.
			System.out.println("saved ");
			
        }
        
        System.out.println(product);
        productRepository.save(product);
/*
        Boolean productinstoreExists = false;
        Productinstore existedProductinstore = new Productinstore();
        Set<Productinstore> productinstores = product.getProductinstores();
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(storeId) == true) {
                productinstoreExists = true;
                existedProductinstore = productinstore;
                break;
            }
        }

        Productinstore newProductinstore = new Productinstore();
        if (productinstoreExists == false) {
            newProductinstore.setProduct(product);
            newProductinstore.setStore(store);
            newProductinstore.setQuantity(quantity);
        } else {
            existedProductinstore.setQuantity(existedProductinstore.getQuantity() + quantity);
            newProductinstore = existedProductinstore;
        }


        product.setQuantity(quantity + product.getQuantity());
        product.getProductinstores().add(newProductinstore);
        productRepository.save(product);
*/
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

        if ((product.getQuantity() - quantity) < 0) {
            throw new NotFoundException("Can not sell " + quantity + " items");
        }

        Productinstore existedProductinstore = new Productinstore();
        Set<Productinstore> productinstores = product.getProductinstores();
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(storeId) == true) {
                if ((productinstore.getQuantity() - quantity) < 0) {
                    throw new NotFoundException("Can not sell " + quantity + " items");
                }

                existedProductinstore = productinstore;
                existedProductinstore.setQuantity(existedProductinstore.getQuantity() - quantity);
                break;
            }
        }

        product.setQuantity(product.getQuantity() - quantity);
        product.getProductinstores().add(existedProductinstore);
        productRepository.save(product);

        return product;
    }
}
