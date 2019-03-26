package com.qader.ahmed.companyandmembers.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.qader.ahmed.companyandmembers.database.dao.ItemDao;
import com.qader.ahmed.companyandmembers.database.entity.Item;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

}