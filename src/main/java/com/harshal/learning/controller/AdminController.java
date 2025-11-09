package com.harshal.learning.controller;

import com.harshal.learning.cache.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.learning.entity.User;
import com.harshal.learning.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheConfig cacheConfig;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users=userService.getAllEntries();
        if(users!=null&&!users.isEmpty()){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user) {
        userService.saveAdminUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clear-cache")
    public void clearCache(){
        cacheConfig.init();
    }
    
    
}
