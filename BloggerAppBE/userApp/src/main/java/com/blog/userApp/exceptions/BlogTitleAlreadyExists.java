package com.blog.userApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Blog Title Already Exists")
public class BlogTitleAlreadyExists extends Exception{
}
