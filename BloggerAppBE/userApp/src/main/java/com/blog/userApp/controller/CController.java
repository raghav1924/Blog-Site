package com.blog.userApp.controller;


import com.blog.userApp.domain.Blog;
import com.blog.userApp.domain.User;
import com.blog.userApp.exceptions.BlogTitleAlreadyExists;
import com.blog.userApp.exceptions.NoBlogFound;
import com.blog.userApp.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.util.ArrayList;


//@CrossOrigin
@RestController
@RequestMapping("/userApp")
public class CController {
    @Autowired
    private IService iService;


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setBlogList(new ArrayList<>());
        return new ResponseEntity<>(iService.addUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/addBlog")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog, HttpServletRequest request)throws BlogTitleAlreadyExists {
        blog.setCommentList(new ArrayList<>());
        String email=(String) request.getAttribute("userEmail");
        System.out.println("controller "+email);

        return new ResponseEntity<>(iService.addBlog(email,blog), HttpStatus.CREATED);
    }
    @PutMapping("/updateBlog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog, HttpServletRequest request)throws NoBlogFound {
        String email=(String) request.getAttribute("userEmail");
        System.out.println("controller "+email);
        return new ResponseEntity<>(iService.updateBolg(email,blog), HttpStatus.CREATED);
    }

    @PostMapping("/updateComment/{comment}")
    public ResponseEntity<?> updateComment(@RequestBody Blog blog, @PathVariable String comment, HttpServletRequest request)throws NoBlogFound{
//        String email=(String) request.getAttribute("userEmail");
        return new ResponseEntity<>(iService.updateComment(blog,comment),HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getuser(HttpServletRequest request){
        String email=(String) request.getAttribute("userEmail");
        return new ResponseEntity<>(iService.getUserDetails(email), HttpStatus.OK);
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(HttpServletRequest request){
        return new ResponseEntity<>(iService.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBlog")
    public ResponseEntity<?> deleteBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws NoBlogFound {
        String email = (String) httpServletRequest.getAttribute("userEmail");
        iService.deleteBlog(blog,email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @DeleteMapping("/deleteUser/{email}")
//    public ResponseEntity<?> register(@PathVariable String email){
//        iService.deleteUser(email);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
