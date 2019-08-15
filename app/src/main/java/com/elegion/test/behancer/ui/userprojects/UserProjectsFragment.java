package com.elegion.test.behancer.ui.userprojects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.userprojects.UserProjects;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;

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

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private ProjectsAdapter mProjectsAdapter;


    private Storage mStorage;

    /* private*/

    @InjectPresenter
    UserProjectsPresenter mPresenter;

    @ProvidePresenter
    UserProjectsPresenter providePresenter(){
        return new UserProjectsPresenter(mStorage);
    }

    @Override
    protected UserProjectsPresenter getPresenter(){
        return mPresenter;
    }

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
    public void showRefresh() {

        mRefreshOwner.setRefreshState(true);

    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

    }



    @Override
    public void showUserProjects(List<UserProjects> userprojects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
      //  mProjectsAdapter.addData(userprojects,true);

    }
}
