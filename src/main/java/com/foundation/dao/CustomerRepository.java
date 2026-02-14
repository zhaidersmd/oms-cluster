package com.foundation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundation.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
