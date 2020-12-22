package com.bms.product.repository.event;

import com.bms.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
@Slf4j
public class ProductBeforeConvertListener extends AbstractMongoEventListener<Product> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        Product product = event.getSource();
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(
                    OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli());
            log.debug("Populated created date for product {} ", product.getId());
        }
        product.setUpdatedDate(
                OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli()
        );
        log.debug("Populated updated date for product {} ", product.getId());
    }
}