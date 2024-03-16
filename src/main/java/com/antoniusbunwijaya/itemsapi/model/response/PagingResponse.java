package com.antoniusbunwijaya.itemsapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagingResponse<T> {
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private List<T> content;
}
