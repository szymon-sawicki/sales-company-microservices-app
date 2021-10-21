package com.salescompany.orderservice.domain.order.dto;

import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;

import java.time.LocalDateTime;
import java.util.Map;

public class GetOrderDto {

    private Long id;
    private Long customerId;
    private Long managerId;
    private Long shopId;
    private Map<GetProductDto,Integer> productsMap;
    private LocalDateTime dateTime;


}
