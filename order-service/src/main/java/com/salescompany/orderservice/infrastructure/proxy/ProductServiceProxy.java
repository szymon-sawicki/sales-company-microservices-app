package com.salescompany.orderservice.infrastructure.proxy;


import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-service",contextId = "product-inside-order-service")
public interface ProductServiceProxy {

    @GetMapping("/ids")
    List<GetProductDto> findAllByIds(@RequestParam String ids);
}
