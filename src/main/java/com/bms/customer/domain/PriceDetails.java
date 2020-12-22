package com.bms.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PriceDetails {
    @Id
    private String id;
    private Double rate;
}
