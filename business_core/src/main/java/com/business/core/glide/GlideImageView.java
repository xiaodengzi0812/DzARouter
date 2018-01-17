package com.business.core.glide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/26.
 * @Version:1.0.0
 */

public class GlideImageView extends ImageView {

    public GlideImageView(Context context) {
        super(context);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadImage(String imagePath) {
        GlideApp.with(this)
                .asDrawable()
                .transition(DrawableTransitionOptions.withCrossFade(1100))// 变换效果
                .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .load(imagePath)
                .into(this);

    }
}
