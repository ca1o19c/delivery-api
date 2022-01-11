package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.domain.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("delivery-api/v1/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> listAll() {
        var customer = new Customer();

        customer.setId(1L);
        customer.setEmail("caio.antonio.c@outlook.com");
        customer.setName("Caio");
        customer.setPhoneNumber("19 99999-1111");

        return List.of(customer);
    }
}
