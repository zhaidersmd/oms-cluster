package com.foundation.controller;

import com.foundation.model.Customer;
import com.foundation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }
}
