package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.domain.exception.EntityNotFoundException;
import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.model.DeliveryStatus;
import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DeliveryRequestService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerService customerService;

    public DeliveryRequestService(DeliveryRepository deliveryRepository,
                                  CustomerService customerService) {
        this.deliveryRepository = deliveryRepository;
        this.customerService = customerService;
    }

    @Transactional
    public Long requestDelivery(Long customerId, Delivery delivery) {

        var customer = customerService.getById(customerId);

        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setRequestDate(LocalDateTime.now());

        var entity = deliveryRepository.save(delivery);

        return entity.getId();
    }

    public Delivery getById(Long deliveryId) {

        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada."));
    }
}
