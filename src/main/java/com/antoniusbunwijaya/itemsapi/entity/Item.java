package com.antoniusbunwijaya.itemsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)", name = "barcode")
    private String barcode;

    @Column(columnDefinition = "datetime(6)", name = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "VARCHAR(255)", name = "item_code")
    private String itemCode;

    @Column(columnDefinition = "VARCHAR(255)", name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "satuan_id")
    private Satuan satuan;

    @Column(columnDefinition = "datetime(6)", name = "updated_at")
    private LocalDateTime updatedAt;
}
