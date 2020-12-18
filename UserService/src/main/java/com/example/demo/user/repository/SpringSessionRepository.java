package com.example.demo.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.user.model.SpringSession;

public interface SpringSessionRepository extends CrudRepository<SpringSession, String> {

}
