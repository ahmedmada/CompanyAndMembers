package com.qader.ahmed.companyandmembers.view.companylist;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.qader.ahmed.companyandmembers.R;
import com.qader.ahmed.companyandmembers.database.entity.Follow;
import com.qader.ahmed.companyandmembers.model.Company;
import com.qader.ahmed.companyandmembers.model.Member;
import com.qader.ahmed.companyandmembers.repository.RepositoryFollow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MyViewHolder> implements Filterable {

    private List<Member> membersList;
    private List<Member> allmembersList;
    private List<String> follow;

    public MembersAdapter(List<Member> membersList,List<String> follow){
        this.membersList = membersList;
        this.allmembersList = membersList;
        this.follow = follow;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_list_row, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Member member = membersList.get(position);

        holder.member_name.setText(member.getName().getFirst()+" "+member.getName().getLast());
        holder.member_age.setText(member.getAge()+" years");
        holder.member_phone.setText(member.getPhone());
        holder.member_email.setText(member.getEmail());

        holder.memberFollow.setText(member.getEmail());

        if (follow.contains(member.getId())) {
            holder.memberFollow.setText("UnFollow");
        }else {
            holder.memberFollow.setText("Follow");
        }

        holder.memberFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (follow.contains(member.getId())) {
                    RepositoryFollow.getInstance().deleteFollow(new Follow(member.getId()));
                    holder.memberFollow.setText("Follow");
                }else{
                    RepositoryFollow.getInstance().addFollow(new Follow(member.getId()));
                    holder.memberFollow.setText("UnFollow");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView member_name;
        private TextView member_age;
        private TextView member_email;
        private TextView member_phone;

        private Button memberFollow;


        public MyViewHolder(View itemView) {
            super(itemView);
            member_name = itemView.findViewById(R.id.member_name);
            member_age = itemView.findViewById(R.id.member_age);
            member_phone = itemView.findViewById(R.id.member_phone);
            member_email = itemView.findViewById(R.id.member_email);

            memberFollow = itemView.findViewById(R.id.memberFollow);

        }


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    membersList = allmembersList;
                } else {
                    List<Member> filteredList = new ArrayList<>();
                    for (Member row : allmembersList) {

                        if ((row.getName().getFirst()+" "+row.getName().getLast()).toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    membersList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = membersList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                membersList = (ArrayList<Member>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

}