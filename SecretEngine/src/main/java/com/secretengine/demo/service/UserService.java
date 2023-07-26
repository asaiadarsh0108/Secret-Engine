package com.secretengine.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secretengine.demo.EncodeAndDecode;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EncodeAndDecode encodeAndDecode;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		return user;
	}
	
	public User insert(User user) {
		user.setRole("user");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User getById(int id) throws ResourceNotFoundException {
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isEmpty()) {
			throw new ResourceNotFoundException("Invalid ID given");
		}
		
		return userFound.get();
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}
}

