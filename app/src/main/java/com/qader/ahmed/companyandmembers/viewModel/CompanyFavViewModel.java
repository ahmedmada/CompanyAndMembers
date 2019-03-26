package com.qader.ahmed.companyandmembers.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.qader.ahmed.companyandmembers.database.entity.Item;
import com.qader.ahmed.companyandmembers.repository.RepositoryCompanyFav;

import java.util.List;

public class CompanyFavViewModel extends ViewModel {

    private final String TAG = this.getClass().getName();

    private LiveData<List<Item>> listItems;


    public LiveData<List<Item>> getListItems() {
        if (listItems == null){
            Log.e(TAG, "_ListItemsIsNULL");
            listItems = new MutableLiveData<List<Item>>();
            loadItemsFromRepository();
        }
        Log.e(TAG, "_ReturningFromViewModel");
        return listItems;
    }

    private void loadItemsFromRepository() {
        Log.e(TAG, "_LoadFromDB");
        listItems = RepositoryCompanyFav.getInstance().getItems();
    }
}
