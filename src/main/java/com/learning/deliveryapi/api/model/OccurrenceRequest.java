package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learning.deliveryapi.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OccurrenceRequest {

    @NotBlank
    private String description;

    private OffsetDateTime registrationDate;

    public static OccurrenceRequest from(Occurrence occurrence) {
        return OccurrenceRequest.builder()
                .description(occurrence.getDescription())
                .registrationDate(occurrence.getRegistrationDate())
                .build();
    }
}
