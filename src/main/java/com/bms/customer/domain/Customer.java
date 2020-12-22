package com.bms.customer.domain;

import com.bms.common.domain.AuditableEntity;
import com.bms.product.domain.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@Builder
public class Customer extends AuditableEntity {
    @Id
    private String id;
    private String name;
    private String mobile;
    private String village;
    private String fullAddress;

    private Product product;

    private PriceDetails priceDetails;

    private Double totalPrice;
    @Builder.Default
    private boolean deleted = false;

}
