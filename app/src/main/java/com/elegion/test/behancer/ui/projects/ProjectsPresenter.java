package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrew on 07.06.2019.
 */

public class ProjectsPresenter extends BasePresenter {
    private final ProjectsView mView;
    private final Storage mStorage;

    public ProjectsPresenter(ProjectsView view, Storage storage) {
        this.mView = view;
        this.mStorage = storage;
    }

    public void getProjects(){

        mCompositeDisposable.add(ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(response -> mStorage.insertProjects(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable ->mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response ->  mView.showProjects(response.getProjects()),
                        throwable ->  mView.showError()));


    }
    public void openProfileFragment(String username){
        mView.openProfileFragment(username);

    }
}
