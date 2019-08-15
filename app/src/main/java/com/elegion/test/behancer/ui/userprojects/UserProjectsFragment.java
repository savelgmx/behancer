package com.elegion.test.behancer.ui.userprojects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.userprojects.UserProjects;

import java.util.List;

/**
 * Created by andrew on 07.07.2019.
 * По факту, это будет экран, похожий на ProjectsFragment,
 * поэтому нужно по максимуму переиспользовать текущий код,
 * добавив/переписав где надо View и Presenter.
 *
 */

public class UserProjectsFragment extends PresenterFragment
        implements Refreshable,UserProjectsView{

    public static final String USER_PROJECTS_KEY = "USER_PROJECTS_KEY";
/*
    @InjectPresenter*/
    private UserProjectsPresenter mPresenter;
    private Storage mStorage;


public static UserProjectsFragment newInstance(Bundle args){
    UserProjectsFragment fragment = new UserProjectsFragment();
    fragment.setArguments(args);
    return fragment;
}




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
     }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.user_projects);
        }


        mPresenter=new UserProjectsPresenter(mStorage);
        onRefreshData();
    }

    @Override
    public void onRefreshData() {

        //TODO getUserProjects in Presenter
        //TODO solve problem with mUser

      //   mPresenter.getUserProjects(mUser);


          mPresenter.getUserProjects("aarsohottt1b42");

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
    public void showUserProjects(List<UserProjects> userprojects) {

    }
}
