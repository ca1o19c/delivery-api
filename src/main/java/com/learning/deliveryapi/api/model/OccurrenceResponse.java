package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OccurrenceResponse {

    private Long id;
    private String description;
    private OffsetDateTime registrationDate;

    public static OccurrenceResponse from(Occurrence occurrence) {
        return OccurrenceResponse.builder()
                .id(occurrence.getId())
                .description(occurrence.getDescription())
                .registrationDate(occurrence.getRegistrationDate())
                .build();
    }
}
