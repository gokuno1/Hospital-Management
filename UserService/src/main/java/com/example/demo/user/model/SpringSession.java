package com.example.demo.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spring_session")
public class SpringSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRIMARY_ID")
	private String primaryId;
	
	@Column(name = "SESSION_ID")
	private String sessionId;
	
	@Column(name = "CREATION_TIME")
	private Long creationTime;
	
	@Column(name = "LAST_ACCESS_TIME")
	private Long lastAccessTime;
	
	@Column(name = "MAX_INACTIVE_INTERVAL")
	private Integer maxInactiveInterval;
	
	@Column(name = "EXPIRY_TIME")
	private Long expiryTime;
	
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	public String getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public Long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public Integer getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	public void setMaxInactiveInterval(Integer maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}
	public Long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	
	

}
