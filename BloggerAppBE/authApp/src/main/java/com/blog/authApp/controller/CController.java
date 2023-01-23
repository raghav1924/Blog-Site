package com.blog.authApp.controller;


import com.blog.authApp.domain.SignUpData;
import com.blog.authApp.domain.User;
import com.blog.authApp.expceptions.ContactAlreadyRegistered;
import com.blog.authApp.expceptions.EmailAlreadyRegistered;
import com.blog.authApp.expceptions.InvalidCredentials;
import com.blog.authApp.services.IService;
import com.blog.authApp.services.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin
@RestController
@RequestMapping("/authApp")
public class CController {
    @Autowired
    private IService iService;

    @Autowired
    private IToken iToken;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpData signUpData)throws EmailAlreadyRegistered, ContactAlreadyRegistered {
        return new ResponseEntity<>(iService.register(signUpData), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)throws InvalidCredentials {
        User user1=iService.login(user);
        if(user1!=null)return new ResponseEntity<>(iToken.genToken(user1),HttpStatus.CREATED);
        else return new ResponseEntity<>("User Not Found",HttpStatus.EXPECTATION_FAILED);
    }
//    @DeleteMapping("/deleteUser/{email}")
//    public ResponseEntity<?> register(@PathVariable String email){
//        iService.deleteUser(email);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
