package com.example.demo.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo", catalog = "eshop")
public class UserScheduler {

	@Id
	private long counter;

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
}
