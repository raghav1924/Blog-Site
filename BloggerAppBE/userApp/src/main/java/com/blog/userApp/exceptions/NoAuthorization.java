package com.blog.userApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You are not authorized to access this blog")
public class NoAuthorization extends Exception{
}
