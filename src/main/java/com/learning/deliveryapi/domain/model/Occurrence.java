package com.learning.deliveryapi.domain.model;

import com.learning.deliveryapi.api.model.OccurrenceRequest;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "occurrences")
@AllArgsConstructor
@NoArgsConstructor
public class Occurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Delivery delivery;

    private String description;

    @Column(name = "registration_date")
    private OffsetDateTime registrationDate;

    public static Occurrence from(OccurrenceRequest occurrenceRequest) {
        return Occurrence.builder()
                .description(occurrenceRequest.getDescription())
                .registrationDate(occurrenceRequest.getRegistrationDate())
                .build();
    }
}
