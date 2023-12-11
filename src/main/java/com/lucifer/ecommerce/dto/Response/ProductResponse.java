package com.lucifer.ecommerce.dto.Response;

import com.lucifer.ecommerce.dto.ProductDto;

import java.util.List;

public class ProductResponse {
    private List<ProductDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
