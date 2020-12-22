package com.bms.customer.dto;

import com.bms.customer.domain.PriceDetails;
import com.bms.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CustomerDto {
    private String id;
    private String name;
    private String mobile;
    private String village;
    private String fullAddress;

    private Product product;

    private PriceDetails priceDetails;

    private Double totalPrice;


}
