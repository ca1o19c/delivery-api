package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.model.Receiver;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@JsonNaming(SnakeCaseStrategy.class)
public class ReceiverResponse {
    private String name;
    private String street;
    private String number;
    private String complement;
    private String neighbourhood;

    public String getName() {
        return name;
    }

    public ReceiverResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ReceiverResponse setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public ReceiverResponse setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getComplement() {
        return complement;
    }

    public ReceiverResponse setComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public ReceiverResponse setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public static ReceiverResponse from (Receiver receiver) {
        return new ReceiverResponse()
                .setName(receiver.getName())
                .setComplement(receiver.getComplement())
                .setNeighbourhood(receiver.getNeighbourhood())
                .setStreet(receiver.getStreet())
                .setNumber(receiver.getNumber());
    }
}
