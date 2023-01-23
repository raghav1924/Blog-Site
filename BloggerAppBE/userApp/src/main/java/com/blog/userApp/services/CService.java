package com.blog.userApp.services;

import com.blog.userApp.domain.Blog;
import com.blog.userApp.domain.User;
import com.blog.userApp.exceptions.BlogTitleAlreadyExists;
import com.blog.userApp.exceptions.NoBlogFound;
import com.blog.userApp.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CService implements IService {
    @Autowired
    private IRepository iRepository;


    @Override
    public User addUser(User user) {
        return iRepository.insert(user);
    }

    @Override
    public User getUserDetails(String email) {
        return iRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return iRepository.findAll();
    }

    @Override
    public User addBlog(String email, Blog blog)throws BlogTitleAlreadyExists {
        System.out.println("service "+email);
        User user=iRepository.findByEmail(email);
        System.out.println(user.getEmail());
        List<Blog> bloglist=user.getBlogList();

        for (Blog bg: bloglist) {
            if (bg.getTitle().equalsIgnoreCase(blog.getTitle())){
                throw new BlogTitleAlreadyExists();
            }
        }
        blog.setEmail(email);
        System.out.println(blog.getEmail());
        bloglist.add(blog);
        user.setBlogList(bloglist);
        System.out.println(bloglist);
        User user2=iRepository.save(user);
        System.out.println(user2.getBlogList());
        return user2;
    }

    @Override
    public User updateBolg(String email, Blog blog)throws NoBlogFound {
        User user=iRepository.findByEmail(email);
                List<Blog> bloglist=user.getBlogList();

        for (Blog bg: bloglist) {
            if (bg.getTitle().equalsIgnoreCase(blog.getTitle())){
                bg.setContent(blog.getContent());
                bg.setImageUrl(blog.getImageUrl());
                user.setBlogList(bloglist);
                iRepository.save(user);
                return user;
            }
        }
        throw new NoBlogFound();
    }

    @Override
    public User updateComment( Blog blog, String comment)throws NoBlogFound {
        System.out.println(blog.getEmail());
        User user=iRepository.findByEmail(blog.getEmail());
        List<Blog> bloglist=user.getBlogList();

        for (Blog bg: bloglist) {
            if (bg.getTitle().equalsIgnoreCase(blog.getTitle())){
                bg.getCommentList().add(comment);
                bg.setCommentList(bg.getCommentList());
                user.setBlogList(bloglist);
                iRepository.save(user);
                return user;
            }
        }
        throw new NoBlogFound();
    }

    public void deleteBlog(Blog blog, String email) throws NoBlogFound {
        User user =  iRepository.findByEmail(email);
        int count=0;
        for (Blog b: user.getBlogList()){
            if(b.getTitle().equalsIgnoreCase((b.getTitle()))){
                count++;
            }
        }
        if (count==0)
            throw new NoBlogFound();
        List<Blog> existingBlogs = user.getBlogList();
        existingBlogs.remove(blog);
        iRepository.save(user);
    }

//    @Override
//    public void deleteUser(String email) {
//        iRepository.deleteById(email);
//    }
}
