package com.blog.userApp.services;


import com.blog.userApp.domain.Blog;
import com.blog.userApp.domain.User;
import com.blog.userApp.exceptions.BlogTitleAlreadyExists;
import com.blog.userApp.exceptions.NoBlogFound;

import java.util.List;

public interface IService {

    public User addUser(User user);
    public User getUserDetails(String email);
    public List<User> getAllUser();
    public User addBlog(String email, Blog blog)throws BlogTitleAlreadyExists;
    public User updateBolg(String email, Blog blog)throws NoBlogFound;
    public User updateComment(Blog blog,String comment)throws NoBlogFound;
    public void deleteBlog(Blog blog, String email) throws NoBlogFound;
//    public void deleteUser(String email);
}
