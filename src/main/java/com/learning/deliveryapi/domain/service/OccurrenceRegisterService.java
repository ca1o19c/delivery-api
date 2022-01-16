package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.model.Occurrence;
import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OccurrenceRegisterService {

    private final DeliveryRepository deliveryRepository;
    public final DeliveryRequestService deliveryRequestService;

    public OccurrenceRegisterService(DeliveryRepository deliveryRepository,
                                     DeliveryRequestService deliveryRequestService) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryRequestService = deliveryRequestService;
    }

    @Transactional
    public Occurrence occurrenceRegister(Long deliveryId, String description) {
        var delivery = deliveryRequestService.getById(deliveryId);

        return delivery.addOccurrence(description);
    }
}
