package com.blog.authApp.proxy;

import com.blog.authApp.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userApp", url = "localhost:999")
public interface IUserProxy {
    @PostMapping("/userApp/addUser")
    public ResponseEntity<?> sendDTOToUserApp(@RequestBody UserDTO userDTO);
}
