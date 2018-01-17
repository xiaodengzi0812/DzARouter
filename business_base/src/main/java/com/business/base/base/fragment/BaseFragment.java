package com.business.base.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected View mContainer;
    protected boolean mIsInflated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = inflater.inflate(getLayoutResId(), container, false);
        return mContainer;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mContainer != null) {
            onInflated(mContainer, savedInstanceState);
            mIsInflated = true;
        }
    }

    protected abstract int getLayoutResId();
    protected abstract void onInflated(View container, Bundle savedInstanceState);
}
