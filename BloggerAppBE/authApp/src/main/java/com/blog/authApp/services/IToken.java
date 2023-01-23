package com.blog.authApp.services;


import com.blog.authApp.domain.User;

import java.util.Map;

public interface IToken {
    public Map<String,String> genToken(User user);
}
