package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.model.Occurrence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OccurrenceRegisterService {

    public final DeliveryRequestService deliveryRequestService;

    public OccurrenceRegisterService(final DeliveryRequestService deliveryRequestService) {
        this.deliveryRequestService = deliveryRequestService;
    }

    @Transactional
    public Occurrence occurrenceRegister(Long deliveryId, String description) {
        var delivery = deliveryRequestService.getById(deliveryId);

        return delivery.addOccurrence(description);
    }

    public List<Occurrence> getById(Long deliveryId) {
        var delivery = deliveryRequestService.getById(deliveryId);

        return delivery.getOccurrences();
    }
}
