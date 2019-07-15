package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.userprojects.UserProjects;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by andrew on 07.07.2019.
 */

public interface UserProjectsView extends BaseView {
    void showUserProjects(@NonNull List<UserProjects> userprojects);
}
