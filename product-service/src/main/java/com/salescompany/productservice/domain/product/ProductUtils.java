package com.salescompany.productservice.domain.product;

import java.util.function.Function;

public interface ProductUtils {
    Function<Product,Long> toId = product -> product.id;
}
