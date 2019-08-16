package com.elegion.test.behancer.ui.userprojects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.model.userprojects.UserProjects;


import java.util.ArrayList;
import java.util.List;

public class UserProjectsAdapter extends RecyclerView.Adapter<UserProjectsHolder> {
    @NonNull
    private final List<UserProjects> mUserProjects = new ArrayList<>();

    @Override
    public UserProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_projects, parent, false);
        return new UserProjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProjectsHolder holder, int position) {
        UserProjects userProjects = mUserProjects.get(position);
        holder.bind(userProjects);

    }

    @Override
    public int getItemCount() {
        return mUserProjects.size();
    }

    public void addData(List<UserProjects>data,boolean isRefreshed){
        if (isRefreshed){
            mUserProjects.clear();
        }

        mUserProjects.addAll(data);
        notifyDataSetChanged();
    }

}
