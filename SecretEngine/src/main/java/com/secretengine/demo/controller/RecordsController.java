package com.secretengine.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.secretengine.demo.entity.Records;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.service.RecordsService;

@RestController
@RequestMapping("/records")
public class RecordsController {

	@Autowired
	private RecordsService recordsService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addRecord(@RequestBody Records records){
		Records rec = recordsService.insert(records);
		return new ResponseEntity<>(rec, HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public List<Records> getAll() {
		return recordsService.getAll();
	}
	
}
