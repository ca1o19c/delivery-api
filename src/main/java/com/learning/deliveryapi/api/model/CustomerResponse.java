package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Customer;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@JsonNaming(SnakeCaseStrategy.class)
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public CustomerResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerResponse setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse()
                .setId(customer.getId())
                .setEmail(customer.getEmail())
                .setName(customer.getName())
                .setPhoneNumber(customer.getPhoneNumber());
    }
}
