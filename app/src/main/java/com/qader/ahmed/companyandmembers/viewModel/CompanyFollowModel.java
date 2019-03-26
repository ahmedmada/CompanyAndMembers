package com.qader.ahmed.companyandmembers.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;
import com.qader.ahmed.companyandmembers.repository.RepositoryCompanyFav;
import com.qader.ahmed.companyandmembers.repository.RepositoryFollow;

import java.util.List;

public class CompanyFollowModel extends ViewModel {

    private final String TAG = this.getClass().getName();

    private LiveData<List<Follow>> followItems;


    public LiveData<List<Follow>> getListItems() {
        if (followItems == null){
            Log.e(TAG, "_ListItemsIsNULL");
            followItems = new MutableLiveData<List<Follow>>();
            loadItemsFromRepository();
        }
        Log.e(TAG, "_ReturningFromViewModel");
        return followItems;
    }

    private void loadItemsFromRepository() {
        Log.e(TAG, "_LoadFromDB");
        followItems = RepositoryFollow.getInstance().getFollow();
    }
}
