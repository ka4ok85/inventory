package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Orphan;;

@Repository
public interface OrphanRepository extends CrudRepository<Orphan, Long> {

}

