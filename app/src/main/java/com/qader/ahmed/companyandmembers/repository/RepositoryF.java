package com.qader.ahmed.companyandmembers.repository;

import android.arch.lifecycle.LiveData;

import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;

import java.util.List;

public interface RepositoryF {

    void addFollow(Follow follow);
    void deleteFollow(Follow follow);
    LiveData<List<Follow>> getFollow();

}