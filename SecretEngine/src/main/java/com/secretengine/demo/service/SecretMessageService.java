package com.secretengine.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretengine.demo.EncodeAndDecode;
import com.secretengine.demo.entity.SecretMessage;
import com.secretengine.demo.repository.SecretMessageRepo;

@Service
public class SecretMessageService {

	@Autowired
	private SecretMessageRepo secretMessageRepo ;
	
	@Autowired
	private EncodeAndDecode encodeAndDecode;
	
	public SecretMessage insert(SecretMessage secretMessage) {
		secretMessageRepo.save(secretMessage);
		return secretMessage;
	}

	public List<SecretMessage> getAll() {
		return secretMessageRepo.findAll();
		
	}

	public SecretMessage getById(int id) throws ResourceNotFoundException {
		Optional<SecretMessage> optional = secretMessageRepo.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid ID given");
		}
		return optional.get();
	}

	public void delete(int id) {
		secretMessageRepo.deleteById(id);
	}

	public SecretMessage decode(SecretMessage secretMessage) {
		secretMessage.setMessage(encodeAndDecode.decode(secretMessage.getMessage(),2));
		return secretMessage;
	}

	public List<SecretMessage> getByUserId(int userid) {
		return secretMessageRepo.getByUserId(userid);
	}
	

}
