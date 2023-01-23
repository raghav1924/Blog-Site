package com.blog.authApp.services;

import com.blog.authApp.domain.SignUpData;
import com.blog.authApp.domain.User;
import com.blog.authApp.expceptions.ContactAlreadyRegistered;
import com.blog.authApp.expceptions.EmailAlreadyRegistered;
import com.blog.authApp.expceptions.InvalidCredentials;

public interface IService {

    public User register(SignUpData signUpData)throws EmailAlreadyRegistered, ContactAlreadyRegistered;
    public User login(User user)throws InvalidCredentials;
//    public void deleteUser(String email);
}
