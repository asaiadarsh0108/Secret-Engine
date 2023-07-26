package com.secretengine.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Repository;

import com.secretengine.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username=?1")
	public User getUserByUsername(String username);

}
