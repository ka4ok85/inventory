package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Store;
import com.example.entity.Userstore;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;
    
    public ProductService() {
    }

    public Product scanProduct(Long productId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        product.setQuantity(quantity + product.getQuantity());
        productRepository.save(product);

        return product;
    }

    public Product sellProduct(Long productId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        if ((product.getQuantity() - quantity) < 0) {
        	System.out.println("ProductService: Can not sell " + quantity + " items");
            throw new NotFoundException("Can not sell " + quantity + " items");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        return product;
    }

    public Product addProduct(String sku, String name) {
        Product product = new Product();
        product.setSku(sku);
        product.setName(name);
        product.setQuantity((long) 0);
        productRepository.save(product);

        return product;
    }
    
    public Iterable<Product> getAllProductsByStore(Long storeId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        return productRepository.findAllProductsInStore(storeId);
    }
    
    public Product findProduct(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }
        
    	return product;
    }
}
