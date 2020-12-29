package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.admin.model.AdminModel;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Integer>{
	
	@Query(value = "delete from admin_details where doctorContact=?1", nativeQuery = true)
	public void deleteBycontactName(String contactName);

}
