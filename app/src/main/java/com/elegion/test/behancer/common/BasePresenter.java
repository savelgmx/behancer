package com.elegion.test.behancer.common;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by andrew on 07.06.2019.
 */

public class BasePresenter {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    //нужна для отписок от подписок (Subscibe в RxJava2)

    public void disposeAll(){
        mCompositeDisposable.clear();
    }
}
