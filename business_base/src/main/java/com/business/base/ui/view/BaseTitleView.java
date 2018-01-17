package com.business.base.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.base.R;
import com.business.base.ui.view.listener.BaseTitleListener;

/**
 * Created by hua on 2017/10/24.
 */

public class BaseTitleView extends FrameLayout {

    private ImageView mLeftDefaultView;
    private TextView mRightDefaultView;
    private TextView mCenterDefaultView;

    private ViewGroup mLeftGroupView;
    private ViewGroup mRightGroupView;
    private ViewGroup mCenterGroupView;

    private BaseTitleListener mListener;


    public BaseTitleView(@NonNull Context context) {
        this(context, null);
    }

    public BaseTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.base__title_view, this);

        mLeftGroupView = (ViewGroup)findViewById(R.id.base__title_view__left);
        mRightGroupView = (ViewGroup)findViewById(R.id.base__title_view__right);
        mCenterGroupView = (ViewGroup)findViewById(R.id.base__title_view__center);

        mLeftDefaultView = (ImageView) findViewById(R.id.base__title_view__left_default);
        mRightDefaultView = (TextView) findViewById(R.id.base__title_view__right_default);
        mCenterDefaultView = (TextView) findViewById(R.id.base__title_view__center_default);

        initViewClickedListener();
    }

    public void setBaseTitleListener(BaseTitleListener l) {
        mListener = l;
    }
    public void setLeftIcon(@DrawableRes int resId) {
        mLeftDefaultView.setImageResource(resId);
    }

    public void setCenterTitle(String centerTitle) {
        mCenterDefaultView.setText(centerTitle);
    }

    public void setRightTitle(String rightTitle) {
        mRightDefaultView.setText(rightTitle);
    }

    public void setLeftView(View leftView, ViewGroup.LayoutParams lp) {
        mLeftGroupView.removeAllViews();
        mLeftGroupView.addView(leftView, lp);
    }

    public void setRightView(View rightView, ViewGroup.LayoutParams lp) {
        mRightGroupView.removeAllViews();
        mRightGroupView.addView(rightView, lp);
    }

    private void initViewClickedListener() {
        mLeftGroupView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onLeftGroupViewClicked(v);
                }
            }
        });
        mRightGroupView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onRightGroupViewClicked(v);
                }
            }
        });
        mCenterDefaultView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCenterTitleViewClicked(v);
                }
            }
        });
    }
}
