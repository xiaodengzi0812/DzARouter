package com.business.core.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.business.core.R;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/11/22.
 * @Version:1.0.0
 */
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)// 使用本地缓存
                .skipMemoryCache(false)// 使用内存缓存)
                .placeholderOf(R.drawable.loading)// 占位符
                .error(R.drawable.error));// 错误图
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        new OkHttpLibraryGlideModule().registerComponents(context, glide, registry);
    }
}
