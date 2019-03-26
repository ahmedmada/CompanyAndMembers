package com.qader.ahmed.companyandmembers.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ComapnyFav")
public class Item {

    @PrimaryKey
    @NonNull
    private String id ;

    @Ignore
    public Item(){

    }

    public Item(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}