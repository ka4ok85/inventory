package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Productlocation;
import com.example.entity.Store;
import com.example.entity.Storelocation;

@Repository
public interface ProductlocationRepository extends CrudRepository<Productlocation, Long> {

    
	// should not use native query here.
	// find store_location by Store, shelf and slot.
	// find product_location by store_location and product. effective enough ?
	@org.springframework.data.jpa.repository.Query(value = "SELECT pl.* FROM product_locations pl INNER JOIN store_locations sl ON sl.id=pl.store_location_id WHERE sl.store_id= ?0 AND sl.shelf= ?1 AND sl.slot= ?2", nativeQuery = true)
    Productlocation findByStoreIdAndShelfAndSlot(Long storeId, Long shelf, Long slot);
}

