package com.blog.authApp.services;


import com.blog.authApp.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CToken implements IToken{

    @Override
    public Map<String, String> genToken(User user) {

        Map<String,String> result=new HashMap<>();
        Map<String,Object> userData=new HashMap<>();
        user.setPassword("");
        userData.put("userEmail",user.getEmail());
        userData.put("userName",user.getName());
        String tokenGen= Jwts.builder().setClaims(userData).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512,"private_key").compact();
        result.put("token",tokenGen);
        result.put("userEmail",user.getEmail());
        result.put("userName",user.getName());
        return result;
    }
}
