package com.harshal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.learning.entity.User;
import com.harshal.learning.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/healthcheck")
	public String healthcheck() {
		return "OK";
	}

	@PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.saveNewUserEntry(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
