package com.elegion.test.behancer.ui.userprojects;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
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
        implements Refreshable,UserProjectsView,ProjectsAdapter.OnItemClickListener{

    public static final String USER_PROJECTS_KEY = "USER_PROJECTS_KEY";

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;

    private Storage mStorage;
    private String mUsername;
    private ProjectsAdapter mProjectsAdapter;


    @InjectPresenter
    UserProjectsPresenter mPresenter;



    @ProvidePresenter
    UserProjectsPresenter providePresenter(){
        return new UserProjectsPresenter(this,mStorage);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }

        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }
    @Nullable
    @Override



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(ProfileFragment.PROFILE_KEY);
        }
        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }


        mProjectsAdapter = new ProjectsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);

        onRefreshData();
    }

    @Override
    public void onRefreshData() {

        mPresenter.getProjects(mUsername);

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
public void showProjects(@NonNull List<Project> projects) {
    mErrorView.setVisibility(View.GONE);
    mRecyclerView.setVisibility(View.VISIBLE);
    mProjectsAdapter.addData(projects, true);
}


    @Override
    public void onItemClick(String username) {

    }
}
