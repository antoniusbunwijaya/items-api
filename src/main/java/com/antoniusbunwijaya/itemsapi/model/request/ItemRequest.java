package com.antoniusbunwijaya.itemsapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemRequest {
    private String barcode;

    private String itemCode;

    private String itemName;

    private String description;

}
