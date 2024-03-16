package com.antoniusbunwijaya.itemsapi.service;

import com.antoniusbunwijaya.itemsapi.model.request.ItemRequest;
import com.antoniusbunwijaya.itemsapi.model.response.ApiResponse;
import com.antoniusbunwijaya.itemsapi.model.response.ItemResponse;
import com.antoniusbunwijaya.itemsapi.model.response.ItemsResponse;
import com.antoniusbunwijaya.itemsapi.model.response.PagingResponse;

public interface ItemService {
    ApiResponse<ItemResponse> createItem(ItemRequest itemRequest);

    ApiResponse<ItemResponse> updateItem(int id, ItemRequest itemRequest);

    ApiResponse<Void> deleteItem(int id);

    ApiResponse<ItemResponse> getItemById(int id);

    PagingResponse<ItemResponse> getAllItems(int page, int size);


    ItemsResponse getListOfItems();
}
