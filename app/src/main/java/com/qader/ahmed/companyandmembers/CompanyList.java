package com.qader.ahmed.companyandmembers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qader.ahmed.companyandmembers.view.companylist.CompanyListFragment;
import com.qader.ahmed.companyandmembers.view.companylist.CompanyDetails;

import java.util.List;

public class CompanyList extends AppCompatActivity {

    CompanyDetails companyDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CompanyListFragment.newInstance())
                    .commitNow();
        }
    }

}
