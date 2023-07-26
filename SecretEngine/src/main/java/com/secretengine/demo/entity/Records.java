package com.secretengine.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Records {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String createdBy;
	
	private LocalDateTime time;
	
	private String filename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
