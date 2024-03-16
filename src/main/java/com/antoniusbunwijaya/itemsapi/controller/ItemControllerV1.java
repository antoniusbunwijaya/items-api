package com.antoniusbunwijaya.itemsapi.controller;

import com.antoniusbunwijaya.itemsapi.model.request.ItemRequest;
import com.antoniusbunwijaya.itemsapi.model.response.ApiResponse;
import com.antoniusbunwijaya.itemsapi.model.response.ItemResponse;
import com.antoniusbunwijaya.itemsapi.model.response.ItemsResponse;
import com.antoniusbunwijaya.itemsapi.model.response.PagingResponse;
import com.antoniusbunwijaya.itemsapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {

    private final ItemService itemService;

    @Autowired
    public ItemControllerV1(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponse>> createItem(@RequestBody ItemRequest itemRequest) {
        ApiResponse<ItemResponse> response = itemService.createItem(itemRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponse>> updateItem(@PathVariable int id, @RequestBody ItemRequest itemRequest) {
        ApiResponse<ItemResponse> response = itemService.updateItem(id, itemRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable int id) {
        ApiResponse<Void> response = itemService.deleteItem(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponse>> getItemById(@PathVariable int id) {
        ApiResponse<ItemResponse> response = itemService.getItemById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagingResponse<ItemResponse>> getAllItems(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        PagingResponse<ItemResponse> response = itemService.getAllItems(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ItemsResponse> getListOfItems() {
        ItemsResponse response = itemService.getListOfItems();
        return ResponseEntity.ok(response);
    }
}
