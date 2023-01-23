package com.blog.userApp.repository;

import com.blog.userApp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepository extends MongoRepository<User,Integer> {

    public User findByEmail(String email);
}
