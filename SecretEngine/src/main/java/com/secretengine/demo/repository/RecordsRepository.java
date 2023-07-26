package com.secretengine.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.secretengine.demo.entity.Records;

public interface RecordsRepository extends JpaRepository<Records, Integer>{

	@Query(value="insert into records (filename, createdBy, time) VALUES (:filename, :createdBy, :time)", nativeQuery=true)
	Records insert(String filename, String createdBy, LocalDateTime time);
	
}
