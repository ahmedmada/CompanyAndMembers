package com.qader.ahmed.companyandmembers.view.companylist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.qader.ahmed.companyandmembers.R;
import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.database.entity.Item;
import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.repository.RepositoryCompanyFav;
import com.qader.ahmed.companyandmembers.repository.RepositoryFollow;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class companiesAdapter extends RecyclerView.Adapter<companiesAdapter.MyViewHolder> implements Filterable {

    private List<Company> companiesList;
    private List<Company> allCompaniesList;
    private List<String> list;
    private List<String> follow;
    private MembersAdapter membersAdapter;
    private Context context;

    Typeface face;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView companyName,companyDescription,companyWebsite;
        ImageView companyIcon,addToFav;
        RecyclerView recycler_view_horizontal;
        Button companyFollow;

        public MyViewHolder(View view) {
            super(view);
            companyName = (TextView) view.findViewById(R.id.companyName);
            companyDescription = (TextView) view.findViewById(R.id.companyDescription);
            companyWebsite = (TextView) view.findViewById(R.id.companyWebsite);
            companyIcon = (ImageView) view.findViewById(R.id.companyIcon);
            addToFav = (ImageView) view.findViewById(R.id.addToFav);
            companyFollow = (Button) view.findViewById(R.id.companyFollow);
            recycler_view_horizontal = (RecyclerView) view.findViewById(R.id.recycler_view_horizontal);

        }
    }

    public companiesAdapter(List<Company> companiesList, List<String> list, List<String> follow, Context context) {
        this.companiesList = companiesList;
        this.allCompaniesList = companiesList;
        this.list = list;
        this.follow = follow;
        this.context = context;
        face = Typeface.createFromAsset(context.getAssets(),
                "fonts/open_sans_condensed_light.ttf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Company company = companiesList.get(position);
        holder.companyName.setText(company.getCompany());
        holder.companyDescription.setText(company.getAbout());
        holder.companyDescription.setTypeface(face);


        holder.companyWebsite.setText(company.getWebsite());
        Picasso.get()
                .load(company.getLogo())
                .error(R.drawable.ic_image_black_24dp)
                .into(holder.companyIcon);

        Log.v("vvvvvvvvvvv","company.getId() = "+company.getId());

        if (list.contains(company.getId())) {
            holder.addToFav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }else {
            holder.addToFav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        }
        if (follow.contains(company.getId())) {
            holder.companyFollow.setText("UnFollow");
        }else {
            holder.companyFollow.setText("Follow");
        }

        holder.recycler_view_horizontal.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        membersAdapter = new MembersAdapter(company.getMembers(),follow);
        holder.recycler_view_horizontal.setAdapter(membersAdapter);

        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.contains(company.getId())) {
                    RepositoryCompanyFav.getInstance().deleteItem(new Item(company.getId()));
                    holder.addToFav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                }else{
                    RepositoryCompanyFav.getInstance().addItem(new Item(company.getId()));
                    holder.addToFav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
        holder.companyFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (follow.contains(company.getId())) {
                    RepositoryFollow.getInstance().deleteFollow(new Follow(company.getId()));
                    holder.companyFollow.setText("Follow");
                }else{
                    RepositoryFollow.getInstance().addFollow(new Follow(company.getId()));
                    holder.companyFollow.setText("UnFollow");
                }
            }
        });

        holder.companyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,CompanyDetails.class);
                i.putExtra("list", (Serializable) list);
                i.putExtra("follow",(Serializable) follow);
                i.putExtra("company", company);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companiesList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    companiesList = allCompaniesList;
                } else {
                    List<Company> filteredList = new ArrayList<>();
                    for (Company row : allCompaniesList) {

                        if (row.getCompany().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    companiesList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = companiesList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                companiesList = (ArrayList<Company>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }
}