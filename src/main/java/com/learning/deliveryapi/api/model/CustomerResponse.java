package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Setter
@Builder
@AllArgsConstructor
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

    public static CustomerResponse valueof (Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
