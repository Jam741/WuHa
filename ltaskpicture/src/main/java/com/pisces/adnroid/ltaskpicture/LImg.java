package com.pisces.adnroid.ltaskpicture;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;

import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by Chris Li on 2017/8/27.
 */

public class LImg {
    private Context context;                        //
    private Object url;                             //图片的地址
    private ImageView imageViewId;                  //显示图片控件地址
    private int errorId = 0;                          //当图片加载出错显示的图片id
    private Drawable errorDrawable;                 //
    private int resourceId = 0;                       //控件占位符
    private Drawable resourceDrawable;              //控件占位符
    private Priority priority;                      //优先权
    private DiskCacheStrategy cache;                //缓存

    public LImg(Context context) {
        this.context = context;
    }


    public static LImg with(Context context) {
        return new LImg(context);
    }

    public static LImg with(Fragment fragment) {
        return new LImg(fragment.getActivity());
    }

    public static LImg with(View view) {
        return new LImg(view.getContext());
    }

    public static LImg with(FragmentActivity fragmentActivity) {
        return new LImg(fragmentActivity);
    }

    public static LImg with(Activity activity) {
        return new LImg(activity);
    }

    public static LImg with(android.support.v4.app.Fragment fragment) {
        return new LImg(fragment.getActivity());
    }


    /*优先权*/
    public LImg priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /*占位图片*/
    public LImg placeholder(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /*占位图片*/
    public LImg placeholder(Drawable resourceDrawable) {
        this.resourceDrawable = resourceDrawable;
        return this;
    }

    /*出错填充图片*/
    public LImg error(int errorId) {
        this.errorId = errorId;
        return this;
    }

    /*出错填充图片*/
    public LImg error(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
        return this;
    }


    /*需要显示的图片*/
    public LImg load(Object url) {
        this.url = url;
        return this;
    }


    /**
     * 缓存机制（glide自带的
     *
     * @param cache DiskCacheStrategy.NONE      什么都不缓存
     *              DiskCacheStrategy.SOURCE    仅仅只缓存原来的全分辨率的图像
     *              DiskCacheStrategy.RESULT    仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
     *              DiskCacheStrategy.ALL       缓存所有版本的图像（**默认行为**）
     */
    public LImg diskCacheStrategy(DiskCacheStrategy cache) {
        this.cache = cache;
        return this;
    }


    /*显示的image*/
    public void into(ImageView imageViewId) {
        this.imageViewId = imageViewId;

//        Glide.with(context).load(url).crossFade().into(imageViewId);

    }
}
