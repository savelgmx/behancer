package com.elegion.test.behancer.ui.userprojects;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by andrew on 13.07.2019.
 */
@InjectViewState
public class UserProjectsPresenter extends BasePresenter<UserProjectsView> {

    private final UserProjectsView mView;
    private final Storage mStorage;

    public UserProjectsPresenter(UserProjectsView view,Storage storage){
        mStorage=storage;
        mView = view;
    }

/*
    public void getUserProjects(String mUser){

        mCompositeDisposable.add(ApiUtils.getApiService().getUserProjects(mUser)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(mStorage::insertUserProjects)
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getUserProjects(mUser) : null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showRefresh())
                        .doFinally(getViewState()::hideRefresh)
                        .subscribe(
                                response -> getViewState().showUserProjects(response.getUserProjects()),
                                        throwable->getViewState().showError())
                                );

    }
*/


    public void getProjects(String mUsername) {

        mCompositeDisposable.add(
                ApiUtils.getApiService().getUserProjects(mUsername)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(mStorage::insertProjects)
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(mView::hideRefresh)
                        .subscribe(
                                response -> mView.showProjects(response.getProjects()),
                                throwable -> mView.showError())
        );
    }

}
