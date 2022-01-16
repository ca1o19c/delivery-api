package com.learning.deliveryapi.domain.model;

import com.learning.deliveryapi.api.model.DeliveryRequest;
import com.learning.deliveryapi.domain.exception.BusinessException;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.learning.deliveryapi.domain.model.DeliveryStatus.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Customer customer;

    @Embedded
    private Receiver receiver;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> ocurrences = new ArrayList<>();

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "request_date")
    private OffsetDateTime requestDate;

    @Column(name = "finished_date")
    private OffsetDateTime finishedDate;

    public static Delivery from(DeliveryRequest deliveryRequest) {
        return Delivery.builder()
                .receiver(Receiver.from(deliveryRequest.getReceiver()))
                .deliveryFee(deliveryRequest.getDeliveryFee())
                .build();
    }

    public void finalizeDelivery() {

        checksITsPossibleToFinalizeOrCancel();

        this.setStatus(FINISHED);
        this.setFinishedDate(OffsetDateTime.now());
    }

    public void cancelDelivery() {

        checksITsPossibleToFinalizeOrCancel();

        this.setStatus(CANCELED);
    }

    public void checksITsPossibleToFinalizeOrCancel() {
        if (!PENDING.equals(this.getStatus()))
            throw new BusinessException("Unable to finalize or cancel delivery with " + this.getStatus() + " status");
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

    public Occurrence addOccurrence(String description) {
        var occurrence = new Occurrence();

        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOcurrences().add(occurrence);

        return occurrence;
    }
}
