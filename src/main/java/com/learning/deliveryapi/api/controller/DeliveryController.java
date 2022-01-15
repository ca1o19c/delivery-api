package com.learning.deliveryapi.api.controller;

import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.service.DeliveryRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("delivery-api/v1/deliveries")
public class DeliveryController {

    private final DeliveryRequestService deliveryRequestService;

    public DeliveryController(DeliveryRequestService deliveryRequestService) {
        this.deliveryRequestService = deliveryRequestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Delivery> getAll() {
        return deliveryRequestService.getAllDeliveries();
    }

    @PostMapping("/{customer-id}/request-delivery")
    public ResponseEntity<Void> request(
            @PathVariable(name = "customer-id") Long customerId,
            @Valid @RequestBody Delivery delivery,
            UriComponentsBuilder builder) {
        var newDeliveryId = deliveryRequestService.requestDelivery(customerId, delivery);

        return ResponseEntity.created(builder.path("delivery-api/v1/deliveries/{delivery-id}")
                .buildAndExpand(newDeliveryId).toUri()).build();
    }

    @GetMapping("/{delivery-id}")
    public ResponseEntity<Delivery> getById(@PathVariable(name = "delivery-id") Long deliveryId) {
        return ResponseEntity.ok(deliveryRequestService.getById(deliveryId));
    }
}
