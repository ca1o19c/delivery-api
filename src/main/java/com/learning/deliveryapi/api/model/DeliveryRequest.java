package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class DeliveryRequest {

    private CustomerRequest customer;

    @Valid
    private ReceiverRequest receiver;

    @NotNull
    private BigDecimal deliveryFee;
}
