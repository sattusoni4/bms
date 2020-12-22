package com.bms.product.domain;

import com.bms.common.domain.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AuditableEntity {
    @Id
    private String id;
    private String name;

    @Builder.Default
    private boolean deleted = false;
}
