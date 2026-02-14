package com.foundation.event;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CustomerEvent {
	
	private String eventType; 
    private Long customerId;
    private String name;
    private String email;
    private LocalDateTime eventTime;
    
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getEventTime() {
		return eventTime;
	}
	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}
    
    

}
