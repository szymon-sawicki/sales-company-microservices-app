package com.salescompany.orderservice.infrastructure.controller;

import com.salescompany.orderservice.application.service.OrdersService;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import com.salescompany.orderservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")

public class OrderController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseDataDto<GetOrderDto> orderCreation(@RequestBody CreateUpdateOrderDto createUpdateOrderDto) {
        return ResponseDataDto.toResponse(ordersService.create(createUpdateOrderDto));
    }

    @GetMapping("/{id}")
    public ResponseDataDto<GetOrderDto> findOrderById(@PathVariable Long id) {
        return ResponseDataDto.toResponse(ordersService.findById(id));
    }

    @GetMapping("/shop/{id}")
    public ResponseDataDto<List<GetOrderDto>> findAllByShop(@PathVariable Long id) {
        return ResponseDataDto.toResponse(ordersService.findAllByShop(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseDataDto<List<GetOrderDto>> findAllByCustomer(@PathVariable Long id) {
        return ResponseDataDto.toResponse(ordersService.findAllByCustomer(id));
    }

    @GetMapping("/manager/{id}")
    public ResponseDataDto<List<GetOrderDto>> findAllByManager(@PathVariable Long id) {
        return ResponseDataDto.toResponse(ordersService.findAllByManager(id));
    }

    @PutMapping("/{id}")
    public ResponseDataDto<GetOrderDto> updateOrder(@PathVariable Long id, @RequestBody CreateUpdateOrderDto createUpdateOrderDto) {
        return ResponseDataDto.toResponse(ordersService.update(id, createUpdateOrderDto));
    }

    @DeleteMapping("/{id}")
    public ResponseDataDto<GetOrderDto> deleteOrder(@PathVariable Long id) {
        return ResponseDataDto.toResponse(ordersService.delete(id));
    }

    @GetMapping("/ids")
    public ResponseDataDto<List<GetOrderDto>> getAllByIds(@RequestParam String ids) {
        var idsConverted = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return ResponseDataDto.toResponse(ordersService.findAllByIds(idsConverted));
    }

}
