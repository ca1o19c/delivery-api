package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.api.model.CustomerRequest;
import com.learning.deliveryapi.api.model.CustomerResponse;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("delivery-api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAll() {
        var response = customerService.getAllCustomers();

        return response
                .stream()
                .map(CustomerResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable(name = "customer-id") Long customerId) {
        var entity = customerService.getById(customerId);
        var response = CustomerResponse.from(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createNewCustomer(
            @Valid @RequestBody CustomerRequest request,
            UriComponentsBuilder builder) {

        var entity = Customer.from(request);

        var newCustomerId = customerService.saveCustomer(entity);

        return ResponseEntity.created(builder.path("delivery-api/v1/customers/{customer-id}")
                .buildAndExpand(newCustomerId).toUri()).build();
    }

    @PutMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(
            @Valid @RequestBody CustomerRequest request,
            @PathVariable(name = "customer-id") Long customerId) {

        var entity = Customer.from(request);

        customerService.updateCustomer(entity, customerId);
    }

    @DeleteMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(
            @PathVariable(name = "customer-id") Long customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
