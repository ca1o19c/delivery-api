package com.learning.deliveryapi.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ReceiverRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String neighbourhood;
}
