package com.foundation.service;

import com.foundation.dao.CustomerRepository;
import com.foundation.event.CustomerEvent;
import com.foundation.model.Customer;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventProducer eventProducer;


    public CustomerService(CustomerRepository customerRepository, CustomerEventProducer eventProducer) {
        this.customerRepository = customerRepository;
        this.eventProducer = eventProducer;
    }

    public Customer createCustomer(Customer customer) {
        Customer saved = customerRepository.save(customer);

        CustomerEvent event = new CustomerEvent();
        event.setEventType("CREATED");
        event.setCustomerId(saved.getId());
        event.setName(saved.getName());
        event.setEmail(saved.getEmail());
        event.setEventTime(LocalDateTime.now());


        eventProducer.publishEvent(event);

        return saved;

    }
}
