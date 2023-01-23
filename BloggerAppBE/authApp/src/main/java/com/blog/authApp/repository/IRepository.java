package com.blog.authApp.repository;

import com.blog.authApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepository extends JpaRepository<User,Integer> {

    public User findByEmailAndPassword(String email,String password);
    public User findByEmail(String email);
    public User findByPhoneNo(String phoneNo);
}
