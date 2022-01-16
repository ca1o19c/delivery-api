package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelDeliveryService {

    public final DeliveryRequestService deliveryRequestService;
    public final DeliveryRepository deliveryRepository;

    Logger logger = LoggerFactory.getLogger(CancelDeliveryService.class);

    public CancelDeliveryService(final DeliveryRequestService deliveryRequestService,
                                 final DeliveryRepository deliveryRepository) {
        this.deliveryRequestService = deliveryRequestService;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public void cancelDelivery(Long deliveryId) {
        var delivery = deliveryRequestService.getById(deliveryId);

        delivery.cancelDelivery();

        logger.info("Canceling delivery with id: {}", delivery.getId());

        deliveryRepository.save(delivery);
    }
}
