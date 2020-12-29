package com.example.demo.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.user.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
