package com.qader.ahmed.companyandmembers.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.qader.ahmed.companyandmembers.MyApp;
import com.qader.ahmed.companyandmembers.database.FollowDatabase;
import com.qader.ahmed.companyandmembers.database.entity.Follow;

import java.util.List;


public class RepositoryFollow implements RepositoryF{

    private static RepositoryFollow repository = new RepositoryFollow();
    private FollowDatabase followDB;

    // operations constants
    private static final int INSERT_OPERATION = 0;
    private static final int DELETE_OPERATION = 1;

    // constructor
    private RepositoryFollow(){
//        repository = new RepositoryCompanyFav();
        initDB();
    }

    public static RepositoryF getInstance(){
//        repository = new RepositoryCompanyFav();
        return repository;
    }

    private void initDB() {
//        Log.e(TAG, "_DataBaseInit");
        followDB = Room.databaseBuilder(MyApp.getContext().getApplicationContext(),
                FollowDatabase.class, "follow").allowMainThreadQueries().build();
    }

//    @Override
//    public void addItem(Item item) {
////        Log.e(TAG, "_ItemIsAddedToDB");
//    }

//    @Override
//    public void deleteItem(Item item) {
////        Log.e(TAG, "_ItemIsDeletedFromDB");
//    }

//    @Override
//    public LiveData<List<Item>> getItems() {
////        Log.e(TAG, "_GetItemsFromDB");
//    }

    @Override
    public void addFollow(Follow follow) {
        new DataBaseOperation(follow,INSERT_OPERATION).execute();

    }

    @Override
    public void deleteFollow(Follow follow) {
        followDB.followDao().delete(follow);
    }

    @Override
    public LiveData<List<Follow>> getFollow() {
        return followDB.followDao().getFollows();
    }

    // Async
    class DataBaseOperation extends AsyncTask<Void,Void,Void> {

        public Follow follow;
        public int operation;

        public DataBaseOperation(Follow follow, int operation){
            this.follow = follow;
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Log.e(TAG, "_doInBackgroundInvoked");
            // INSERT operation
            if (operation == INSERT_OPERATION) followDB.followDao().insert(follow);
            // else delete ...
            return null;
        }

    }

}
