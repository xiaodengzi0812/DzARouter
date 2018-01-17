package com.business.core.view;

import android.content.Context;
import android.graphics.PaintFlagsDrawFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import com.business.base.view.recycler.refresh.RefreshCreator;
import com.business.core.R;

/**
 * @Title: 默认样式的刷新头部辅助类
 * @Author: Djk
 * @Time: 2017/9/22
 * @Version:1.0.0
 */
public class DefaultRefreshCreator extends RefreshCreator {
    // 加载数据的ImageView
    private View mRefreshIv;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        final View refreshView = LayoutInflater.from(context).inflate(R.layout.refresh_header_view, parent, false);
        mRefreshIv = refreshView.findViewById(R.id.refresh_iv);
        return refreshView;
    }

    @Override
    public void onPull(float currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = currentDragHeight / refreshViewHeight;
        mRefreshIv.setRotation(rotate * 360);
    }

    @Override
    public void onRefreshing() {
        // 刷新的时候不断旋转
        RotateAnimation animation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(1000);
        mRefreshIv.startAnimation(animation);
    }

    @Override
    public void onStopRefresh() {
        // 停止加载的时候清除动画
        mRefreshIv.setRotation(0);
        mRefreshIv.clearAnimation();
    }
}
