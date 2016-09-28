package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Iterable<Product> findAllProductsInStore(@Param("storeId") Long store);
}

