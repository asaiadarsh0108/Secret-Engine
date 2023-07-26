package com.secretengine.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secretengine.demo.entity.Records;

public interface RecordsRepository extends JpaRepository<Records, Integer>{

}
