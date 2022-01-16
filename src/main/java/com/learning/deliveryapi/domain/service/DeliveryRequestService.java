package com.learning.deliveryapi.domain.service;

import com.learning.deliveryapi.api.model.DeliveryRequest;
import com.learning.deliveryapi.domain.exception.EntityNotFoundException;
import com.learning.deliveryapi.domain.model.Delivery;
import com.learning.deliveryapi.domain.model.DeliveryStatus;
import com.learning.deliveryapi.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

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
    public Long requestDelivery(Long customerId, DeliveryRequest request) {

        var customer = customerService.getById(customerId);

        var entity = Delivery.from(request);

        entity.setCustomer(customer);
        entity.setStatus(DeliveryStatus.PENDING);
        entity.setRequestDate(OffsetDateTime.now());

        var save = deliveryRepository.save(entity);

        return save.getId();
    }

    public Delivery getById(Long deliveryId) {

        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found."));
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
