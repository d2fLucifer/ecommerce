package com.lucifer.ecommerce.dto.Response;

import com.lucifer.ecommerce.dto.ReviewDto;

import java.util.List;

public class ReviewResponse {
    private List<ReviewDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
