package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizeDeliveryService {

    public final DeliveryRequestService deliveryRequestService;
    public final DeliveryRepository deliveryRepository;

    Logger logger = LoggerFactory.getLogger(FinalizeDeliveryService.class);

    public FinalizeDeliveryService(DeliveryRequestService deliveryRequestService, DeliveryRepository deliveryRepository) {
        this.deliveryRequestService = deliveryRequestService;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public void finalizeDelivery(Long deliveryId) {
        var delivery = deliveryRequestService.getById(deliveryId);

        delivery.finalizeDelivery();

        logger.info("Finalizing delivery with id: {}", delivery.getId());

        deliveryRepository.save(delivery);
    }
}
