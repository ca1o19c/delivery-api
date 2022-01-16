package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.api.model.DeliveryRequest;
import com.learning.deliveryapi.api.model.DeliveryResponse;
import com.learning.deliveryapi.domain.service.DeliveryRequestService;
import com.learning.deliveryapi.domain.service.FinalizeDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("delivery-api/v1/deliveries")
public class DeliveryController {

    private final DeliveryRequestService deliveryRequestService;
    private final FinalizeDeliveryService finalizeDeliveryService;

    Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    public DeliveryController(DeliveryRequestService deliveryRequestService, FinalizeDeliveryService finalizeDeliveryService) {
        this.deliveryRequestService = deliveryRequestService;
        this.finalizeDeliveryService = finalizeDeliveryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveryResponse> getAll() {
        var response = deliveryRequestService.getAllDeliveries();

        return response
                .stream()
                .map(DeliveryResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @PutMapping("/{delivery-id}/finalize")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizeDelivery(@PathVariable(name = "delivery-id") Long deliveryId) {
        finalizeDeliveryService.finalizeDelivery(deliveryId);
    }

    @PostMapping("/{customer-id}/request-delivery")
    public ResponseEntity<Void> register(
            @PathVariable(name = "customer-id") Long customerId,
            @Valid @RequestBody DeliveryRequest request,
            UriComponentsBuilder builder) {
        var newDeliveryId = deliveryRequestService.requestDelivery(customerId, request);

        return ResponseEntity.created(builder.path("delivery-api/v1/deliveries/{delivery-id}")
                .buildAndExpand(newDeliveryId).toUri()).build();
    }

    @GetMapping("/{delivery-id}")
    public ResponseEntity<DeliveryResponse> getById(@PathVariable(name = "delivery-id") Long deliveryId) {
        var entity = deliveryRequestService.getById(deliveryId);
        var response = DeliveryResponse.from(entity);

        return ResponseEntity.ok(response);
    }
}
