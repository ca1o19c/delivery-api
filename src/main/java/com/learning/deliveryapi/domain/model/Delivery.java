package com.learning.deliveryapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Customer customer;

    @Embedded
    private Receiver receiver;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "finished_date")
    private LocalDateTime finishedDate;
}
