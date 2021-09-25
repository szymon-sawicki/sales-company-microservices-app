package com.salescompany.productsservice.domain.product.dto;

import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.product.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateProductDto {
    String name;
    BigDecimal price;
    Category category;
    GetProducerDto producer;


}
