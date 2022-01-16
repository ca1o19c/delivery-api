package com.learning.deliveryapi.domain.model;

import com.learning.deliveryapi.api.model.ReceiverRequest;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_street")
    private String street;

    @Column(name = "receiver_number")
    private String number;

    @Column(name = "receiver_complement")
    private String complement;

    @Column(name = "receiver_neighbourhood")
    private String neighbourhood;

    public static Receiver valueof(ReceiverRequest receiverRequest) {
        return Receiver.builder()
                .name(receiverRequest.getName())
                .street(receiverRequest.getStreet())
                .number(receiverRequest.getNumber())
                .neighbourhood(receiverRequest.getNeighbourhood())
                .complement(receiverRequest.getComplement())
                .build();
    }

}
