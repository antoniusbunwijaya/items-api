package com.antoniusbunwijaya.itemsapi.service;

import com.antoniusbunwijaya.itemsapi.entity.Item;
import com.antoniusbunwijaya.itemsapi.entity.Satuan;
import com.antoniusbunwijaya.itemsapi.model.request.ItemRequest;
import com.antoniusbunwijaya.itemsapi.model.response.*;
import com.antoniusbunwijaya.itemsapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ApiResponse<ItemResponse> createItem(ItemRequest itemRequest) {
        Item newItem = new Item();
        newItem.setBarcode(itemRequest.getBarcode());
        newItem.setItemCode(itemRequest.getItemCode());
        newItem.setItemName(itemRequest.getItemName());
        newItem.setCreatedAt(LocalDateTime.now());
        newItem.setUpdatedAt(LocalDateTime.now());

        if (itemRequest.getDescription() != null) {
            Satuan satuan = new Satuan();
            satuan.setDescription(itemRequest.getDescription());
            newItem.setSatuan(satuan);
        }

        Item savedItem = itemRepository.save(newItem);
        return new ApiResponse<>(true, "Item berhasil dibuat", mapToItemResponse(savedItem));
    }

    @Override
    public ApiResponse<ItemResponse> updateItem(int id, ItemRequest itemRequest) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setBarcode(itemRequest.getBarcode());
            existingItem.setItemCode(itemRequest.getItemCode());
            existingItem.setItemName(itemRequest.getItemName());
            existingItem.setUpdatedAt(LocalDateTime.now());

            if (itemRequest.getDescription() != null) {
                Satuan satuan = existingItem.getSatuan();
                if (satuan == null) {
                    satuan = new Satuan();
                }
                satuan.setDescription(itemRequest.getDescription());
                existingItem.setSatuan(satuan);
            }

            Item updatedItem = itemRepository.save(existingItem);
            return new ApiResponse<>(true, "Item berhasil di perbaharui", mapToItemResponse(updatedItem));
        } else {
            return new ApiResponse<>(false, "Item tidak ditemukan", null);
        }
    }

    @Override
    public ApiResponse<Void> deleteItem(int id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.delete(optionalItem.get());
            return new ApiResponse<>(true, "Item berhasil dihapus", null);
        } else {
            return new ApiResponse<>(false, "Item tidak ditemukan", null);
        }
    }

    @Override
    public ApiResponse<ItemResponse> getItemById(int id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.map(item -> new ApiResponse<>(true, "Item ditemukan", mapToItemResponse(item)))
                .orElseGet(() -> new ApiResponse<>(false, "Item tidak ditemukan", null));
    }

    @Override
    public PagingResponse<ItemResponse> getAllItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Item> itemPage = itemRepository.findAll(pageable);
        List<ItemResponse> itemResponses = itemPage.getContent().stream()
                .map(this::mapToItemResponse)
                .collect(Collectors.toList());
        return new PagingResponse<>(itemPage.getNumber(), itemPage.getTotalPages(), itemPage.getTotalElements(), itemResponses);
    }

    @Override
    public ItemsResponse getListOfItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> itemResponses = items.stream()
                .map(this::mapToItemResponse)
                .collect(Collectors.toList());
        return new ItemsResponse(itemResponses);
    }

    private ItemResponse mapToItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getBarcode(),
                item.getCreatedAt(),
                item.getItemCode(),
                item.getItemName(),
                mapToSatuanResponse(item.getSatuan()),
                item.getUpdatedAt()
        );
    }

    private SatuanResponse mapToSatuanResponse(Satuan satuan) {
        if (satuan != null) {
            return new SatuanResponse(
                    satuan.getId(),
                    satuan.getDescription(),
                    satuan.getCreatedAt(),
                    satuan.getUpdatedAt()
            );
        } else {
            return null;
        }
    }
}
