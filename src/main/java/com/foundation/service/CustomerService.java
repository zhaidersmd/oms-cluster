package com.foundation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundation.dao.CustomerRepository;
import com.foundation.dao.OutboxRepository;
import com.foundation.event.CustomerEvent;
import com.foundation.model.Customer;
import com.foundation.model.OutboxEvent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public CustomerService(CustomerRepository customerRepository, OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Customer createCustomer(Customer customer) throws Exception{
        Customer saved = customerRepository.save(customer);

        CustomerEvent event = new CustomerEvent();
        event.setEventType("CREATED");
        event.setCustomerId(saved.getId());
        event.setName(saved.getName());
        event.setEmail(saved.getEmail());
        event.setEventTime(LocalDateTime.now());


        OutboxEvent outbox = new OutboxEvent();
        outbox.setEventType("CUSTOMER_CREATED");
        outbox.setPayload(objectMapper.writeValueAsString(event));
        outbox.setStatus("NEW");
        outbox.setCreatedAt(LocalDateTime.now());

        outboxRepository.save(outbox);

        return saved;

    }
}
