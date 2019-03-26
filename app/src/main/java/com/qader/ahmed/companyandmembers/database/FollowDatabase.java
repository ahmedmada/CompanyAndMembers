package com.qader.ahmed.companyandmembers.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.qader.ahmed.companyandmembers.database.dao.FollowDao;
import com.qader.ahmed.companyandmembers.database.dao.ItemDao;
import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;

@Database(entities = {Follow.class}, version = 1, exportSchema = false)
public abstract class FollowDatabase extends RoomDatabase {

    public abstract FollowDao followDao();

}