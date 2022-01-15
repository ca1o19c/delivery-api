package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.model.DeliveryStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@JsonNaming(SnakeCaseStrategy.class)
public class DeliveryResponse {

    private Long id;
    private CustomerResponse customer;
    private ReceiverResponse receiver;
    private BigDecimal deliveryFee;
    private DeliveryStatus status;
    private OffsetDateTime finishedDate;
    private OffsetDateTime requestDate;

    public Long getId() {
        return id;
    }

    public DeliveryResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public DeliveryResponse setCustomer(CustomerResponse customer) {
        this.customer = customer;
        return this;
    }

    public ReceiverResponse getReceiver() {
        return receiver;
    }

    public DeliveryResponse setReceiver(ReceiverResponse receiver) {
        this.receiver = receiver;
        return this;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public DeliveryResponse setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public DeliveryResponse setStatus(DeliveryStatus status) {
        this.status = status;
        return this;
    }

    public OffsetDateTime getFinishedDate() {
        return finishedDate;
    }

    public DeliveryResponse setFinishedDate(OffsetDateTime finishedDate) {
        this.finishedDate = finishedDate;
        return this;
    }

    public OffsetDateTime getRequestDate() {
        return requestDate;
    }

    public DeliveryResponse setRequestDate(OffsetDateTime requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    public static DeliveryResponse from(Delivery delivery) {
        return new DeliveryResponse()
                .setId(delivery.getId())
                .setReceiver(ReceiverResponse.from(delivery.getReceiver()))
                .setCustomer(CustomerResponse.from(delivery.getCustomer()))
                .setDeliveryFee(delivery.getDeliveryFee())
                .setStatus(delivery.getStatus())
                .setFinishedDate(delivery.getFinishedDate())
                .setRequestDate(delivery.getRequestDate());

    }
}
