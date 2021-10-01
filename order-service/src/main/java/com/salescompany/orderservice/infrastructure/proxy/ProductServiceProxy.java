package com.salescompany.orderservice.infrastructure.proxy;


import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "product-service",contextId = "inside-order-service")
public interface ProductServiceProxy {

    @GetMapping("/byId")
    List<GetProductDto> findAllByIds(@RequestParam String ids);


}
