package com.learning.deliveryapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Addressee {

    @Column(name = "addressee_name")
    private String name;

    @Column(name = "addressee_street")
    private String street;

    @Column(name = "addressee_number")
    private String number;

    @Column(name = "addressee_complement")
    private String complement;

    @Column(name = "addressee_neighbourhood")
    private String neighbourhood;
}
