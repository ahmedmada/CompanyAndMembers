package com.qader.ahmed.companyandmembers.viewModel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.repository.CompanieRepository;

import java.util.List;

public class CampaniesListViewModel extends ViewModel {

    private MutableLiveData<List<Company>> data;
    private CompanieRepository CompanyModel;

    public CampaniesListViewModel() {
        CompanyModel = new CompanieRepository();
    }

    public void init() {
        if (this.data != null)
            return;

        data = CompanyModel.getCompanies();
    }

    public void sort(List<Company> data) {
        this.data.setValue(data);
    }

    public MutableLiveData<List<Company>> getCompanies() {
        return this.data;
    }
}
