package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Receiver;
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
public class ReceiverResponse {
    private String name;
    private String street;
    private String number;
    private String complement;
    private String neighbourhood;

    public static ReceiverResponse from(Receiver receiver) {
        return ReceiverResponse.builder()
                .name(receiver.getName())
                .complement(receiver.getComplement())
                .neighbourhood(receiver.getNeighbourhood())
                .street(receiver.getStreet())
                .number(receiver.getNumber())
                .build();
    }
}
