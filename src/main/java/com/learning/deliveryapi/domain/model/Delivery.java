package com.learning.deliveryapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
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

    @Valid
    @Embedded
    private Receiver receiver;

    @NotNull
    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "finished_date")
    private LocalDateTime finishedDate;
}
