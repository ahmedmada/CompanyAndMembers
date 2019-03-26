package com.qader.ahmed.companyandmembers.repository;

import android.arch.lifecycle.MutableLiveData;

import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.network.APIInterface;
import com.qader.ahmed.companyandmembers.network.ApiClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanieRepository {

    private APIInterface apiInterface;


    public CompanieRepository() {
    }

    public MutableLiveData<List<Company>> getCompanies() {
        final MutableLiveData<List<Company>> refferAndInvitePojoMutableLiveData = new MutableLiveData<>();
        apiInterface = ApiClient.getRetrofitInstance().create(APIInterface.class);

        apiInterface.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Company>>() {
                    @Override
                    public void onSuccess(List<Company> Companies) {
                        // Received all notes
                        refferAndInvitePojoMutableLiveData.setValue(Companies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Network error
                    }
                });



//        Call<List<Company>> call = apiInterface.getdata();
//        call.enqueue(new Callback<List<Company>>() {
//            @Override
//            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
//                if(response.body()!=null) {
//                    refferAndInvitePojoMutableLiveData.setValue(response.body());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Company>> call, Throwable t) {
//
//            }
//        });

        return refferAndInvitePojoMutableLiveData;
    }


}
