package com.foundation.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.foundation.event.CustomerEvent;

@Service
public class CustomerEventProducer {
	
	
	private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
	
	private static final String TOPIC  = "customer-events";
	
	public CustomerEventProducer(KafkaTemplate<String, CustomerEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishEvent(CustomerEvent event) {
		kafkaTemplate.send(TOPIC, event);
	}

}
