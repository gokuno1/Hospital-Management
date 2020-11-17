
  package com.example.demo.user.repository;
  
  import org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.data.jpa.repository.Query; import
  org.springframework.stereotype.Repository;
  
  import com.example.demo.user.model.UserScheduler;
  
  @Repository 
  public interface SchedulerCOunt extends JpaRepository<UserScheduler, Long> {
  
  @Query(value = "select count(*) as counter from userdetails", nativeQuery=true)
  UserScheduler userCounter();
  
  }
 