package com.elegion.test.behancer.common;

import android.support.v4.app.Fragment;

/**
 * Created by andrew on 07.06.2019.
 */


public abstract class PresenterFragment<P extends BasePresenter> extends Fragment {

    protected abstract P getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }
}
