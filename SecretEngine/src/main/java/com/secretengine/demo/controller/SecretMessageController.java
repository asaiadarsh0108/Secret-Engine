package com.secretengine.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretengine.demo.entity.Records;
import com.secretengine.demo.entity.SecretMessage;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.service.RecordsService;
import com.secretengine.demo.service.ResourceNotFoundException;
import com.secretengine.demo.service.SecretMessageService;
import com.secretengine.demo.service.UserService;

@RestController
@RequestMapping("/secretmessage")
public class SecretMessageController {
		
		@Autowired
		private SecretMessageService secretMessageService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private RecordsService recordsService;
		
		private Records records;
		
		@Autowired
		private EncodeAndDecode encodeAndDecode;
		
		@PostMapping("/add/{userid}")
		public SecretMessage CreateSecretMessage(@RequestBody SecretMessage secretMessage,@PathVariable int userid) {
			try {
				User user=userService.getById(userid);
				secretMessage.setUser(user);
				records.setCreatedBy(user.getUsername());
				records.setFilename(secretMessage.getName());
				records.setTime(LocalDateTime.now());
				recordsService.save(records);
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			secretMessage.setMessage(encodeAndDecode.encode(secretMessage.getMessage()));
			return secretMessageService.insert(secretMessage);
		}
		
		@GetMapping("/all/{userid}")
		public List<SecretMessage> getAll(@PathVariable int userid) {
		List<SecretMessage> scList = null;
			try {
				userService.getById(userid);
				 scList =  secretMessageService.getByUserId(userid);
			} catch (ResourceNotFoundException e) {
				System.out.println(e.getMessage());
			}
			return scList;
		}
		
		@GetMapping("/one/{userid}/{id}")
		public ResponseEntity<?> getOne(@PathVariable int id, @PathVariable int userid) {
			try {
				User  user = userService.getById(userid);
				SecretMessage secret = secretMessageService.getById(id);
				if(secret.getUser().getId()==user.getId()){
					secret = secretMessageService.decode(secret);
				}
				return ResponseEntity.status(HttpStatus.OK).body(secret);
			} catch (ResourceNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		
		@PutMapping("/update/{id}")
		public ResponseEntity<?> update(@PathVariable int id, @RequestBody SecretMessage secretMessage) {
			try {
				secretMessageService.getById(id);
			} catch (ResourceNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
			
			secretMessage.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(secretMessageService.insert(secretMessage));
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> delete(@PathVariable int id) {
			try {
				secretMessageService.getById(id);
			} catch (ResourceNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(e.getMessage());
			}
			
			secretMessageService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
}
