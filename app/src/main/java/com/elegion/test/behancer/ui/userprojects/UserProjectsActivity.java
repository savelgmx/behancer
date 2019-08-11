package com.elegion.test.behancer.ui.userprojects;

import android.support.v4.app.Fragment;
import com.elegion.test.behancer.common.SingleFragmentActivity;

/**
 * Created by Administrator on 10.07.2019.
 */

public class UserProjectsActivity extends SingleFragmentActivity {
    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {

        if (getIntent()!= null){
            return UserProjectsFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");


    }
}


