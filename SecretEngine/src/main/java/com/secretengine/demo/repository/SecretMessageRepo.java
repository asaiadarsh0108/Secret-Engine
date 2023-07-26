package com.secretengine.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.secretengine.demo.entity.SecretMessage;

public interface SecretMessageRepo extends JpaRepository<SecretMessage, Integer>{
	
	@Query("select s from SecretMessage s where s.user.id=?1")
	List<SecretMessage> getByUserId(int userid);

}
