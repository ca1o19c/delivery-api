package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.api.model.OccurrenceRequest;
import com.learning.deliveryapi.api.model.OccurrenceResponse;
import com.learning.deliveryapi.domain.model.Occurrence;
import com.learning.deliveryapi.domain.service.OccurrenceRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("delivery-api/v1/deliveries/{delivery-id}/occurrences")
public class OccurrenceController {

    private final OccurrenceRegisterService occurrenceRegisterService;
    Logger logger = LoggerFactory.getLogger(OccurrenceController.class);

    public OccurrenceController(OccurrenceRegisterService occurrenceRegisterService) {
        this.occurrenceRegisterService = occurrenceRegisterService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceResponse register(
            @PathVariable(name = "delivery-id") Long deliveryId,
            @Valid @RequestBody OccurrenceRequest request) {

        var description = Occurrence.from(request).getDescription();

        var occurrence = occurrenceRegisterService.occurrenceRegister(deliveryId, description);

        return OccurrenceResponse.from(occurrence);
    }
}
