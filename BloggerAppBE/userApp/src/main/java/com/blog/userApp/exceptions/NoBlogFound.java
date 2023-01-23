package com.blog.userApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No blog with mentions details was found")
public class NoBlogFound extends Exception{
}
