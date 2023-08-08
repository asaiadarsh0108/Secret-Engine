package com.secretengine.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretengine.demo.EncryptionUtils;
import com.secretengine.demo.PasswordStrength;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.repository.UserRepository;
import com.secretengine.demo.service.ResourceNotFoundException;
import com.secretengine.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordStrength passwordStrength;
	
	@GetMapping("/login")
	public UserDetails login(Principal principal) {
		String username = principal.getName();
		UserDetails user = userService.loadUserByUsername(username);
		return user;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> postManager(@RequestBody User user) {
		String passwordStrengthError =passwordStrength.checkPasswordStrength(user.getPassword());
		if (passwordStrengthError != null) {
            return new ResponseEntity<>(passwordStrengthError, HttpStatus.BAD_REQUEST);
        }
		 return new ResponseEntity<>(userService.insert(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<User> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		try {
			User user = userService.getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} 
		catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody User user) {
		try {
			userService.getById(id);
			String passwordStrengthError =passwordStrength.checkPasswordStrength(user.getPassword());
			if (passwordStrengthError != null) {
	            return new ResponseEntity<>(passwordStrengthError, HttpStatus.BAD_REQUEST);
	        }
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(userService.insert(user));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			userService.getById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
