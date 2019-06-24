package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.user.User;

/**
 * Created by andrew on 23.06.2019.
 */

public interface ProfileView extends BaseView {

    void showProfile(User user);
}
