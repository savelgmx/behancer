package com.elegion.test.behancer.data.model.userprojects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 09.07.2019.
 */

public class UserProjectsResponse implements Serializable {
    @SerializedName("userprojects")

   private List<UserProjects> mUserProjects;

    public List<UserProjects> getUserProjects() {

        return mUserProjects;
    }


    public void setUserProjects(List<UserProjects> userprojects) {
        mUserProjects = userprojects;
    }
}
