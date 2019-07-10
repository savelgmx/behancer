package com.elegion.test.behancer.ui.userprojects;

import android.support.v4.app.Fragment;
import com.elegion.test.behancer.common.SingleFragmentActivity;

/**
 * Created by Administrator on 10.07.2019.
 */

public class UserProjectsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return UserProjectsFragment.newInstance();
    }
}
