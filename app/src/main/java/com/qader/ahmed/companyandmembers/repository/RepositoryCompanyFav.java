package com.qader.ahmed.companyandmembers.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.qader.ahmed.companyandmembers.MyApp;
import com.qader.ahmed.companyandmembers.database.ItemDatabase;
import com.qader.ahmed.companyandmembers.database.entity.Item;

import java.util.List;


public class RepositoryCompanyFav implements Repository{

    private static RepositoryCompanyFav repository = new RepositoryCompanyFav();
    private ItemDatabase itemDB;
//    private final String TAG = this.getClass().getName();

    // operations constants
    private static final int INSERT_OPERATION = 0;
    private static final int DELETE_OPERATION = 1;

    // constructor
    private RepositoryCompanyFav(){
//        repository = new RepositoryCompanyFav();
        initDB();
    }

    public static Repository getInstance(){
//        repository = new RepositoryCompanyFav();
        return repository;
    }

    private void initDB() {
//        Log.e(TAG, "_DataBaseInit");
        itemDB = Room.databaseBuilder(MyApp.getContext().getApplicationContext(),
                ItemDatabase.class, "ComapnyFav").allowMainThreadQueries().build();
    }

    @Override
    public void addItem(Item item) {
//        Log.e(TAG, "_ItemIsAddedToDB");
        new DataBaseOperation(item,INSERT_OPERATION).execute();
    }

    @Override
    public void deleteItem(Item item) {
//        Log.e(TAG, "_ItemIsDeletedFromDB");
        itemDB.itemDao().delete(item);
    }

    @Override
    public LiveData<List<Item>> getItems() {
//        Log.e(TAG, "_GetItemsFromDB");
        return itemDB.itemDao().getItems();
    }

    // Async
    class DataBaseOperation extends AsyncTask<Void,Void,Void> {

        public Item item;
        public int operation;

        public DataBaseOperation(Item item, int operation){
            this.item = item;
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Log.e(TAG, "_doInBackgroundInvoked");
            // INSERT operation
            if (operation == INSERT_OPERATION) itemDB.itemDao().insert(item);
            // else delete ...
            return null;
        }

    }

}
