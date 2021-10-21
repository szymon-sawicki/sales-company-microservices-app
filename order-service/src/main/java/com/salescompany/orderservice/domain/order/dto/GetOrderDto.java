package com.salescompany.orderservice.domain.order.dto;

import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderDto {

    private Long id;
    private Long customerId;
    private Long managerId;
    private Long shopId;
    private Map<Long,Integer> productsMap;
    private LocalDateTime dateTime;

}
