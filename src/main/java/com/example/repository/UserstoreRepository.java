package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.Userstore;
import com.example.entity.Restatementjob;
import com.example.entity.Store;
import com.example.entity.User;

@Repository
public interface UserstoreRepository extends CrudRepository<Userstore, Long> {

    Userstore findByUserAndStore(User user, Store store);
    Userstore findByUserAndStoreAndStatus(User user, Store store, String status);

    @Query(value = "SELECT u FROM Userstore u LEFT JOIN FETCH u.user where u.store = :store AND u.status = :status")
    Iterable<Userstore> findById(@Param("store") Store store, @Param("status") String status);
}

