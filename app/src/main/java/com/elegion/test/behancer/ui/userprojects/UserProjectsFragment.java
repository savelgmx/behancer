package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.model.user.User;

/**
 * Created by andrew on 07.07.2019.
 * По факту, это будет экран, похожий на ProjectsFragment,
 * поэтому вам нужно по максимуму переиспользовать текущий код,
 * добавив/переписав где надо View и Presenter.
 *
 */

public class UserProjectsFragment extends PresenterFragment
        implements Refreshable,UserProjectsView{


    @Override
    public void onRefreshData() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showUserProjects(User user) {

    }
}
