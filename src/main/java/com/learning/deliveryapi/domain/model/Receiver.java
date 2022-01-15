package com.learning.deliveryapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Embeddable
public class Receiver {

    @NotBlank
    @Column(name = "receiver_name")
    private String name;

    @NotBlank
    @Column(name = "receiver_street")
    private String street;

    @NotBlank
    @Column(name = "receiver_number")
    private String number;

    @Column(name = "receiver_complement")
    private String complement;

    @NotBlank
    @Column(name = "receiver_neighbourhood")
    private String neighbourhood;
}
