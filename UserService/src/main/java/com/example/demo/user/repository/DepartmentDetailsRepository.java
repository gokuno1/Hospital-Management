package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.user.model.DepartmentDetails;

public interface DepartmentDetailsRepository extends CrudRepository<DepartmentDetails, Integer>{
	
	@Query(value = "select * from Department where DEPARTMENT_NAME=?1", nativeQuery = true)
	public DepartmentDetails findByName(String deptName);

}
