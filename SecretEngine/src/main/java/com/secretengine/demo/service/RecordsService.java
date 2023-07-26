package com.secretengine.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretengine.demo.entity.Records;
import com.secretengine.demo.repository.RecordsRepository;

@Service
public class RecordsService {

	@Autowired
	private RecordsRepository recordsRepository;
	
	public void save(Records records) {
		recordsRepository.save(records);
	}

}
