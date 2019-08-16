package com.elegion.test.behancer.ui.userprojects;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.model.userprojects.UserProjects;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

class UserProjectsHolder extends RecyclerView.ViewHolder {
    private ImageView mImage;
    private TextView mName;
    private TextView mUsername;
    private TextView mPublishedOn;


    public UserProjectsHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.image);
        mName = itemView.findViewById(R.id.tv_name);
        mUsername = itemView.findViewById(R.id.tv_username);
        mPublishedOn = itemView.findViewById(R.id.tv_published);
    }

    public void bind(UserProjects userProjectsItem) {

    }
}
