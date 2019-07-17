package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by andrew on 13.07.2019.
 */

public class UserProjectsPresenter extends BasePresenter<UserProjectsView> {

    private final Storage mStorage;

    public UserProjectsPresenter(Storage storage){mStorage=storage;}

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
                                response -> getViewState().showUserProjects((response.getUserProjects())
                                )
                        )
        );


    }


}
