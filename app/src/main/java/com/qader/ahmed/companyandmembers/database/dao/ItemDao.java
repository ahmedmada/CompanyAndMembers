package com.qader.ahmed.companyandmembers.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qader.ahmed.companyandmembers.database.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM ComapnyFav")
    LiveData<List<Item>> getItems();

}