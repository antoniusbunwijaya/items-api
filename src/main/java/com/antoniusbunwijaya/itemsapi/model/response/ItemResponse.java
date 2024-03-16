package com.antoniusbunwijaya.itemsapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {
    private int id;

    private String barcode;

    private LocalDateTime createdAt;

    private String itemCode;

    private String itemName;

    private SatuanResponse satuan;

    private LocalDateTime updatedAt;
}
