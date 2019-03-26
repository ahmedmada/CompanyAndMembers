package com.qader.ahmed.companyandmembers.repository;

import android.arch.lifecycle.LiveData;

import com.qader.ahmed.companyandmembers.database.entity.Item;

import java.util.List;

public interface Repository {

    void addItem(Item item);
    void deleteItem(Item item);
    LiveData<List<Item>> getItems();

}