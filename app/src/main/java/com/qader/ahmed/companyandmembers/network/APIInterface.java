package com.qader.ahmed.companyandmembers.network;

import com.qader.ahmed.companyandmembers.model.Company;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/api/json/get/Vk-LhK44U")
    Single<List<Company>> getdata();
}
