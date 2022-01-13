package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.domain.model.Customer;
import com.learning.deliveryapi.domain.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("delivery-api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> getById(@PathVariable(name = "customer-id") Long customerId) {
        return ResponseEntity.ok(customerService.getById(customerId));
    }

    @PostMapping
    public ResponseEntity<Void> createNewCustomer(
            @Valid @RequestBody Customer customer,
            UriComponentsBuilder builder) {

        var newCustomerId = customerService.saveCustomer(customer);

        return ResponseEntity.created(builder.path("delivery-api/v1/customers/{customerId}")
                .buildAndExpand(newCustomerId).toUri()).build();
    }

    @PutMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(
            @Valid @RequestBody Customer customer,
            @PathVariable(name = "customer-id") Long customerId) {
        customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(
            @PathVariable(name = "customer-id") Long customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
