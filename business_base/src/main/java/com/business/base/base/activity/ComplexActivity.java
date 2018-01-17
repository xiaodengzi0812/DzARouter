package com.business.base.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.business.base.R;
import com.business.base.base.fragment.BaseFragment;


public abstract class ComplexActivity extends BaseTitleActivity {

    protected BaseFragment mFragment;

    protected void replaceFragment(BaseFragment fragment) {
        replaceFragment(fragment, null, false);
    }

    protected void replaceFragment(BaseFragment f, Bundle arguments, boolean isAddToStack) {
        if (isFinishing() || f == null) {
            return;
        }
        mFragment = f;
        if (arguments != null) {
            f.setArguments(arguments);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.base__title_activity__content, f);
        if (isAddToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

}
