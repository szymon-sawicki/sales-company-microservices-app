package com.salescompany.productservice.domain.warrantypolicy;

import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.type.ServiceType;
import com.salescompany.productservice.infrastructure.persistence.entity.WarrantyPolicyEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WarrantyPolicyDomainTest {

    @Test
    @DisplayName("when conversion to get warranty policy dto is correct")
    public void test1() {

        var id = 3L;
        var warrantyPeriod = 24;
        var returningPercent = 50;
        var possibleServices = List.of(ServiceType.REPAIR);
        var processingPeriod = 14;

        var warrantyPolicy = WarrantyPolicy.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .possibleServices(possibleServices)
                .processingPeriod(processingPeriod)
                .build();

        var expectedDto = GetWarrantyPolicyDto.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .possibleServices(possibleServices)
                .processingPeriod(processingPeriod)
                .build();

        assertThat(warrantyPolicy.toGetWarrantyPolicyDto())
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when conversion to get warranty policy dto is correct")
    public void test2() {

        var id = 3L;
        var warrantyPeriod = 24;
        var returningPercent = 50;
        var possibleServices = List.of(ServiceType.REPAIR);
        var processingPeriod = 14;

        var warrantyPolicy = WarrantyPolicy.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .possibleServices(possibleServices)
                .processingPeriod(processingPeriod)
                .build();

        var expectedEntity = WarrantyPolicyEntity.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .possibleServices(possibleServices)
                .processingPeriod(processingPeriod)
                .build();

        assertThat(warrantyPolicy.toEntity())
                .isEqualTo(expectedEntity);

    }
}
