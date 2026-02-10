package com.authservice.core.controller;

import com.authservice.core.service.UserService;
import com.authservice.core.util.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.USERS)
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(ApiConstants.USER_ID)
    public ResponseEntity getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping(ApiConstants.ALL)
    public ResponseEntity getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(ApiConstants.GREET)
    public ResponseEntity<String> greet(@RequestHeader(name = "X-Custom-Header", required = false) String customHeader) {
        String greeting = "Hello, ";
        if (customHeader != null) {
            greeting += customHeader;
        } else {
            greeting += "Guest";
        }
        return ResponseEntity.ok().body(greeting);
    }
}
