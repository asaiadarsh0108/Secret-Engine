package com.secretengine.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretengine.demo.entity.Records;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.repository.RecordsRepository;

@Service
public class RecordsService {

	@Autowired
	private RecordsRepository recordsRepository;

//	public void saveR(String username, String name, LocalDateTime now) {
//		// TODO Auto-generated method stub
//		recordsRepository.insert(username, name, now);
//	}

	public Records insert(Records records) {
		return recordsRepository.save(records);
	}

	public List<Records> getAll() {
		// TODO Auto-generated method stub
		return recordsRepository.findAll();
	}

}
