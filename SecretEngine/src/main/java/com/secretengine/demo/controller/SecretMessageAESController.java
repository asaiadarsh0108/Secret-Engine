package com.secretengine.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretengine.demo.EncryptionUtils;
import com.secretengine.demo.entity.SecretMessage;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.service.ResourceNotFoundException;
import com.secretengine.demo.service.SecretMessageService;
import com.secretengine.demo.service.UserService;

@RestController
@RequestMapping("/secretmessage/aes")
public class SecretMessageAESController {
	
	@Autowired
	private SecretMessageService secretMessageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EncryptionUtils encryptionUtils;
	
	@PostMapping("/add/{userid}")
	public ResponseEntity<?> addMessage(@RequestBody SecretMessage secretMessage, BindingResult bindingResult,@PathVariable int userId){
	if (bindingResult.hasErrors()) {
        return new ResponseEntity<>("Invalid user data. Please check the request body.", HttpStatus.BAD_REQUEST);
    }
	try {
		User user=userService.getById(userId);
		secretMessage.setUser(user);
		String message = encryptionUtils.encryptMessage(secretMessage.getMessage());
	} catch (ResourceNotFoundException e) {
		System.out.println(e.getMessage());
	}
	
}
}
