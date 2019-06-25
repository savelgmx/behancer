package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrew on 23.06.2019.
 */

public class ProfilePresenter extends BasePresenter {
    private final ProfileView mView;
    private final Storage mStorage;
    private String mUsername;

    public ProfilePresenter(ProfileView view,Storage storage){
        this.mView = view;
        this.mStorage = storage;
    }
    public void getProfile(String mUser){
        this.mUsername=mUser;
        //сюда переносим код из getProfile() ProfileFragment
        mCompositeDisposable.add(ApiUtils.getApiService().getUserInfo(this.mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(this.mUsername) :null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> mView.showProfile(response.getUser()),
                        throwable -> mView.showError()));
    }




}
