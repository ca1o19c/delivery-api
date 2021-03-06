package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.exception.BusinessException;
import com.learning.deliveryapi.domain.exception.EntityNotFoundException;
import com.learning.deliveryapi.domain.model.Customer;
import com.learning.deliveryapi.domain.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
    }

    @Transactional
    public Long saveCustomer(Customer customer) {

        logger.info("Creating a new entry in the database. Request: {}", customer);

        var emailInUse = customerRepository.findByEmail(customer.getEmail())
                .stream()
                .anyMatch(existingCustomer -> !existingCustomer.equals(customer));

        if (emailInUse) {
            logger.error("Email {} already exists in the database.", customer.getEmail());
            throw new BusinessException("There is already a customer registered with this email.");
        }

        var save = customerRepository.save(customer);

        return save.getId();
    }

    public void updateCustomer(Customer customer, Long customerId) {

        var entity = getById(customerId);

        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setPhoneNumber(customer.getPhoneNumber());

        saveCustomer(entity);
    }

    public void deleteCustomerById(Long customerId) {
        getById(customerId);

        customerRepository.deleteById(customerId);
    }
}
