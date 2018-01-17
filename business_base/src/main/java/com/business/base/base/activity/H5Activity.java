package com.business.base.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.business.base.base.fragment.H5Fragment;

import static com.business.base.base.common.Constant.H5_KEY_TITLE;
import static com.business.base.base.common.Constant.H5_KEY_URL;

/**
 * Created by hua on 2017/11/13.
 */

public class H5Activity extends ComplexActivity {

    public static void launch(Context context, String url, String title) {
        Intent intent = new Intent(context, H5Activity.class);
        intent.putExtra(H5_KEY_URL, url);
        intent.putExtra(H5_KEY_TITLE, title);
        context.startActivity(intent);
    }
    @Override
    protected void initContentView(View contentView) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        if (null != intent) {
            mTitleView.setCenterTitle(intent.getStringExtra(H5_KEY_TITLE));
            bundle.putString(H5_KEY_URL, intent.getStringExtra(H5_KEY_URL));
        }
        replaceFragment(new H5Fragment(), bundle, false);
    }

    protected H5Fragment newH5Fragment() {
        return new H5Fragment();
    }
}
