package com.salescompany.orderservice.infrastructure.proxy;

import com.salescompany.orderservice.infrastructure.proxy.dto.GetUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service",contextId = "inside-order-service")
public interface UserServiceProxy {

    @GetMapping("/users/{id}")
    public GetUserDto findById(@PathVariable Long id);

}
