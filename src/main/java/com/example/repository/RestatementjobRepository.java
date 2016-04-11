package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Restatementjob;

@Repository
public interface RestatementjobRepository extends CrudRepository<Restatementjob, Long> {
    List<Restatementjob> findByProductAndStatus(Long product, String status);
    List<Restatementjob> findByStoreAndStatus(Long store, String status);
}

