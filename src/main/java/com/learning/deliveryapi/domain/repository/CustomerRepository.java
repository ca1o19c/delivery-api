package com.learning.deliveryapi.domain.repository;

import com.learning.deliveryapi.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);
    Optional<Customer> findByNameContaining(String name);
    Optional<Customer> findByEmail(String email);
}
