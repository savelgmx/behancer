package com.elegion.test.behancer.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.ui.userprojects.UserProjectsActivity;
import com.elegion.test.behancer.ui.userprojects.UserProjectsFragment;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Vladislav Falzan.
 *
 *
 *
 public class ProjectsFragment extends PresenterFragment<ProjectsPresenter>
 implements Refreshable,ProjectsView, ProjectsAdapter.OnItemClickListener {
 */


public class ProfileFragment extends PresenterFragment
        implements Refreshable,ProfileView {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private View mProfileView;
    private String mUsername;
    private Storage mStorage;
    private ProfilePresenter mPresenter;

    private ImageView mProfileImage;
    private TextView mProfileName;
    private TextView mProfileCreatedOn;
    private TextView mProfileLocation;
    private Button mButtonUserProjects;


    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mStorage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;
        mRefreshOwner = context instanceof RefreshOwner ? (RefreshOwner) context : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fr_profile, container, false);

        mButtonUserProjects = view.findViewById(R.id.buttonUserProjects);
        mButtonUserProjects.setOnClickListener(mOnUserProjectsClickListener);

        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mErrorView = view.findViewById(R.id.errorView);
        mProfileView = view.findViewById(R.id.view_profile);

        mProfileImage = view.findViewById(R.id.iv_profile);
        mProfileName = view.findViewById(R.id.tv_display_name_details);
        mProfileCreatedOn = view.findViewById(R.id.tv_created_on_details);
        mProfileLocation = view.findViewById(R.id.tv_location_details);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }

        mProfileView.setVisibility(View.VISIBLE);

        mPresenter = new ProfilePresenter(this,mStorage);

        onRefreshData();
    }

    @Override
    public void onRefreshData() {
        mPresenter.getProfile(mUsername);
    }

    public void bind(User user) {
        Picasso.with(getContext())
                .load(user.getImage().getPhotoUrl())
                .fit()
                .into(mProfileImage);
        mProfileName.setText(user.getDisplayName());
        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mProfileLocation.setText(user.getLocation());
    }

    @Override
    protected ProfilePresenter getPresenter() {
        return null;
    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

        mRefreshOwner.setRefreshState(false);

    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mProfileView.setVisibility(View.GONE);
    }

    @Override
    public void showProfile(User user) {
        mErrorView.setVisibility(View.GONE);
        mProfileView.setVisibility(View.VISIBLE);
        bind(user);

    }
    //На ProfileFragment добавить кнопку,
    // которая ведет на экран со списком проектов этого пользователя - UserProjectsFragment
    private View.OnClickListener mOnUserProjectsClickListener=(viev)-> {

        Intent intent = new Intent(getActivity(), UserProjectsActivity.class);
        Bundle args = new Bundle();
                args.putString(ProfileFragment.PROFILE_KEY, mUsername);
                intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);

    };




}
