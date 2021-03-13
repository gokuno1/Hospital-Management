package com.example.ZuulApiGateway.authtoken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SPRING_SESSION")
public class SpringSession {

 
	private String primaryId;
	private String sessionId;
	private Long creationTime;
	private Long lastAccessTime;
	private Integer maxInactiveInterval;
	private Long expiryTime;
	private String principalName;
	

 
	
	@Column(name="SESSION_ID")
	public String getSessionId() {
		return sessionId;
	}
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="PRIMARY_ID")
	public String getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name="LAST_ACCESS_TIME") 
	public Long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	@Column(name="MAX_INACTIVE_INTERVAL") 
	public Integer getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	public void setMaxInactiveInterval(Integer maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}
	
	@Column(name="EXPIRY_TIME") 
	public Long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}
	
	@Column(name="PRINCIPAL_NAME", length = 500) 
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	
	@Column(name="CREATION_TIME") 
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	

	    
	    
	
}
