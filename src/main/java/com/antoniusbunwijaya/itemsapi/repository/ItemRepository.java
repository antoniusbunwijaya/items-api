package com.antoniusbunwijaya.itemsapi.repository;

import com.antoniusbunwijaya.itemsapi.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Page<Item> findAll(Pageable pageable);
}
