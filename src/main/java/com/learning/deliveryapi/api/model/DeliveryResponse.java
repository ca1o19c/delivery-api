package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DeliveryResponse {

    private Long id;
    private CustomerResponse customer;
    private ReceiverResponse receiver;
    private BigDecimal deliveryFee;
    private DeliveryStatus status;
    private OffsetDateTime finishedDate;
    private OffsetDateTime requestDate;

    public static DeliveryResponse from(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .receiver(ReceiverResponse.from(delivery.getReceiver()))
                .customer(CustomerResponse.from(delivery.getCustomer()))
                .deliveryFee(delivery.getDeliveryFee())
                .status(delivery.getStatus())
                .finishedDate(delivery.getFinishedDate())
                .requestDate(delivery.getRequestDate())
                .build();

    }
}
