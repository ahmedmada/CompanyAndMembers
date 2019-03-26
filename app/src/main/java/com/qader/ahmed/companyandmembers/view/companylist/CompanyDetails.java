package com.qader.ahmed.companyandmembers.view.companylist;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qader.ahmed.companyandmembers.CompanyList;
import com.qader.ahmed.companyandmembers.R;
import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;
import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.repository.RepositoryCompanyFav;
import com.qader.ahmed.companyandmembers.repository.RepositoryFollow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetails extends AppCompatActivity {

    TextView companyName,companyDescription,companyWebsite;
    ImageView companyIcon,addToFav;
    RecyclerView recycler_view_horizontal;
    Button companyFollow;

    Company company;

    private List<String> list;
    private List<String> follow;

    private MembersAdapter membersAdapter;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public static CompanyDetails newInstance() {
        return new CompanyDetails();
    }

    Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_details_fragment);

        companyName = (TextView) findViewById(R.id.companyName);
        companyDescription = (TextView) findViewById(R.id.companyDescription);
        companyWebsite = (TextView) findViewById(R.id.companyWebsite);
        companyIcon = (ImageView) findViewById(R.id.companyIcon);
        addToFav = (ImageView) findViewById(R.id.addToFav);
        companyFollow = (Button) findViewById(R.id.companyFollow);
        recycler_view_horizontal = (RecyclerView) findViewById(R.id.recycler_view_horizontal);

        company = (Company) getIntent().getSerializableExtra("company");
        list = (ArrayList<String>)  getIntent().getSerializableExtra("list");
        follow = (ArrayList<String>)  getIntent().getSerializableExtra("follow");

        companyName.setText(company.getCompany());
        companyDescription.setText(company.getAbout());

        face = Typeface.createFromAsset(getAssets(),
                "fonts/open_sans_condensed_light.ttf");
        companyDescription.setTypeface(face);

        companyWebsite.setText(company.getWebsite());
        Picasso.get()
                .load(company.getLogo())
                .error(R.drawable.ic_image_black_24dp)
                .into(companyIcon);


        if (list.contains(company.getId())) {
            addToFav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }else {
            addToFav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        }
        if (follow.contains(company.getId())) {
            companyFollow.setText("UnFollow");
        }else {
            companyFollow.setText("Follow");
        }

        recycler_view_horizontal.setLayoutManager(new GridLayoutManager(this, 2));
        membersAdapter = new MembersAdapter(company.getMembers(),follow);
        recycler_view_horizontal.setAdapter(membersAdapter);

        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.contains(company.getId())) {
                    RepositoryCompanyFav.getInstance().deleteItem(new Item(company.getId()));
                    addToFav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                }else{
                    RepositoryCompanyFav.getInstance().addItem(new Item(company.getId()));
                    addToFav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
        companyFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (follow.contains(company.getId())) {
                    RepositoryFollow.getInstance().deleteFollow(new Follow(company.getId()));
                    companyFollow.setText("Follow");
                }else{
                    RepositoryFollow.getInstance().addFollow(new Follow(company.getId()));
                    companyFollow.setText("UnFollow");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    membersAdapter.getFilter().filter(newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    membersAdapter.getFilter().filter(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CompanyList.class));
        finish();
    }
}
