package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.learning.deliveryapi.domain.model.DeliveryStatus.FINISHED;
import static com.learning.deliveryapi.domain.model.DeliveryStatus.PENDING;

@Service
public class FinalizeDeliveryService {

    public final DeliveryRequestService deliveryRequestService;
    public final DeliveryRepository deliveryRepository;

    public FinalizeDeliveryService(DeliveryRequestService deliveryRequestService, DeliveryRepository deliveryRepository) {
        this.deliveryRequestService = deliveryRequestService;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public void finalizeDelivery(Long deliveryId) {
        var delivery = deliveryRequestService.getById(deliveryId);

        delivery.finalizeDelivery();

        deliveryRepository.save(delivery);
    }
}
