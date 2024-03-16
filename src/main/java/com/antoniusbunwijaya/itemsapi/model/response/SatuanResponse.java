package com.antoniusbunwijaya.itemsapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class SatuanResponse {
    private int id;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}