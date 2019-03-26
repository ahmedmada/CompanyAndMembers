package com.qader.ahmed.companyandmembers.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;

import java.util.List;

@Dao
public interface FollowDao {

    @Insert
    void insert(Follow follow);

    @Delete
    void delete(Follow follow);

    @Query("SELECT * FROM follow")
    LiveData<List<Follow>> getFollows();

}