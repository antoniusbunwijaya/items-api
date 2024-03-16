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
@Table(name = "tb_satuan")
public class Satuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "datetime(6)", name = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "VARCHAR(255)", name = "description")
    private String description;

    @Column(columnDefinition = "datetime(6)", name = "updated_at")
    private LocalDateTime updatedAt;

}
