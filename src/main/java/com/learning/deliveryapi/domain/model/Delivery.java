package com.learning.deliveryapi.domain.model;

import com.learning.deliveryapi.api.model.DeliveryRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
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
    private OffsetDateTime requestDate;

    @Column(name = "finished_date")
    private OffsetDateTime finishedDate;

    public static Delivery valueof(DeliveryRequest deliveryRequest) {
        return Delivery.builder()
                .customer(Customer.valueof(deliveryRequest.getCustomer()))
                .receiver(Receiver.valueof(deliveryRequest.getReceiver()))
                .deliveryFee(deliveryRequest.getDeliveryFee())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return id != null && Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
