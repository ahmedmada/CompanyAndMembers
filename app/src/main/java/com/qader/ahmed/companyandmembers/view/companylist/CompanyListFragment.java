package com.qader.ahmed.companyandmembers.view.companylist;


import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import android.widget.Toast;

import com.qader.ahmed.companyandmembers.R;
import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;
import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.model.Member;
import com.qader.ahmed.companyandmembers.viewModel.CampaniesListViewModel;
import com.qader.ahmed.companyandmembers.viewModel.CompanyFavViewModel;
import com.qader.ahmed.companyandmembers.viewModel.CompanyFollowModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompanyListFragment extends Fragment {

    private CampaniesListViewModel mViewModel;
    private List<Company> companyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private companiesAdapter mAdapter;

    private List<Item> list = new ArrayList<>();
    private List<String> listId = new ArrayList<>();
    private CompanyFavViewModel companyFavViewModel;

    private List<Follow> listFollow = new ArrayList<>();
    private List<String> followId = new ArrayList<>();
    private CompanyFollowModel companyFollowModel;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public static CompanyListFragment newInstance() {
        return new CompanyListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.company_list_fragment, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mAdapter = new companiesAdapter(companyList,listId,followId,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CampaniesListViewModel.class);
        mViewModel.init();

        mViewModel.getCompanies().observe(this, new Observer<List<Company>>() {
            @Override
            public void onChanged(@Nullable List<Company> companies) {

                companyList  = companies;
                mAdapter = new companiesAdapter(companyList,listId,followId,getContext());
                recyclerView.setAdapter(mAdapter);

            }
        });

        companyFavViewModel = ViewModelProviders.of(this).get(CompanyFavViewModel.class);
        companyFavViewModel.getListItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                list.clear();
                listId.clear();
                list.addAll(companyFavViewModel.getListItems().getValue());

//                if (listId.size() == 0)
//                    listId = new ArrayList<>();
//                else
                for (int i = 0 ; i < list.size() ; i++)
                    listId.add(list.get(i).getId());

                mAdapter = new companiesAdapter(companyList,listId,followId,getContext());

            }
        });

        companyFollowModel = ViewModelProviders.of(this).get(CompanyFollowModel.class);
        companyFollowModel.getListItems().observe(this, new Observer<List<Follow>>() {
            @Override
            public void onChanged(@Nullable List<Follow> items) {
                listFollow.clear();
                followId.clear();
                listFollow.addAll(companyFollowModel.getListItems().getValue());

//                if (listFollow.size() == 0)
//                    followId = new ArrayList<>();
//                else
                for (int i = 0 ; i < listFollow.size() ; i++)
                    followId.add(listFollow.get(i).getId());

                mAdapter = new companiesAdapter(companyList,listId,followId,getContext());

            }
        });


    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    mAdapter.getFilter().filter(newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    mAdapter.getFilter().filter(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.sort_companies_ascending:
                sortCompaniesList();
                mViewModel.sort(companyList);

                return true;

            case R.id.sort_companies_descending:
                sortCompaniesList();
                Collections.reverse(companyList);
                mViewModel.sort(companyList);

                return true;

            case R.id.sort_members_by_name_ascending:
                sortMembersListNameAscending();
                mViewModel.sort(companyList);

                return true;

            case R.id.sort_members_by_name_descending:
                sortMembersListName();
                mViewModel.sort(companyList);

                return true;

            case R.id.sort_members_by_age_ascending:
                sortMembersListAgeAscending();
                mViewModel.sort(companyList);
                return true;

            case R.id.sort_members_by_age_descending:
                sortMembersListAge();
                mViewModel.sort(companyList);

                return true;

            default:
                break;
        }

        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);

    }

    private void sortCompaniesList() {
        Collections.sort(companyList, new Comparator<Company>(){
            public int compare(Company obj1, Company obj2) {
                return obj2.getCompany().compareToIgnoreCase(obj1.getCompany());
            }
        });
    }

    private void sortMembersListName() {
        int length = companyList.size();
        for (int i = 0 ; i < length ; i++){
            Collections.sort(companyList.get(i).getMembers(), new Comparator<Member>(){
                public int compare(Member obj1, Member obj2) {
                    return obj2.getName().getFirst().compareToIgnoreCase(obj1.getName().getFirst());
                }
            });
        }
    }

    private void sortMembersListNameAscending() {
        int length = companyList.size();
        for (int i = 0 ; i < length ; i++){
            Collections.sort(companyList.get(i).getMembers(), new Comparator<Member>(){
                public int compare(Member obj1, Member obj2) {
                    return (obj2.getName().getFirst().compareToIgnoreCase(obj1.getName().getFirst()))*-1;
                }
            });
        }
    }

    private void sortMembersListAge() {
        int length = companyList.size();
        for (int i = 0 ; i < length ; i++){
            Collections.sort(companyList.get(i).getMembers(), new Comparator<Member>(){
                public int compare(Member obj1, Member obj2) {
                    return obj2.getAge() >= obj1.getAge() ? 1:-1;
                }
            });
        }
    }

    private void sortMembersListAgeAscending() {
        int length = companyList.size();
        for (int i = 0 ; i < length ; i++){
            Collections.sort(companyList.get(i).getMembers(), new Comparator<Member>(){
                public int compare(Member obj1, Member obj2) {
                    return obj2.getAge() < obj1.getAge() ? 1:-1;
                }
            });
        }
    }

}
