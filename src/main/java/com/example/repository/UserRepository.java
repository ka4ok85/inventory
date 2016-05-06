package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.User;
import com.example.entity.Userstore;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}

