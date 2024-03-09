package com.api.blog.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class PaginationResponseDTO <T> {
    private List<T> items;
    private long totalItems;
    private int page;
    private int size;
}
