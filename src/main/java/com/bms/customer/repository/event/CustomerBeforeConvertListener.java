package com.bms.customer.repository.event;

import com.bms.customer.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
@Slf4j
public class CustomerBeforeConvertListener extends AbstractMongoEventListener<Customer> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Customer> event) {
        Customer customer = event.getSource();
        if (customer.getCreatedDate() == null) {
            customer.setCreatedDate(
                    OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli());
            log.debug("Populated created date for customer {} ", customer.getId());
        }
        customer.setUpdatedDate(
                OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli()
        );
        log.debug("Populated updated date for customer {} ", customer.getId());
    }
}