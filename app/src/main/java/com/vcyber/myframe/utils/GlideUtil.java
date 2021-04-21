package com.vcyber.myframe.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vcyber.myframe.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Create by zjl on 2019/5/10
 * ---- 图片加载工具类 ----
 * 参考文献：1-https://muyangmin.github.io/glide-docs-cn/
 * 参考文献：2-https://blog.csdn.net/guolin_blog/article/details/78582548
 * 参考文献：高斯模糊、黑白图片效果-https://www.jianshu.com/p/9db8f314b286
 */
public class GlideUtil {

    /**
     * 加载图片
     */
    public static void loadPicture(Context mContext, String path, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .centerCrop()
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .into(imageView);//view
    }

    /**
     * 加载图片带缩略图功能
     */
    public static void loadPictureThumbnail(Context mContext, String path, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .into(imageView);//view
    }

    /**
     * 加载圆形图片
     */
    public static void loadPictureCircleCrop(Context mContext, String path, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))//圆形图片
                .into(imageView);//view
    }

    /**
     * 加载圆角图片
     */
    public static void loadPictureRoundedCorners(Context mContext, String path, ImageView imageView, int radius) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))//圆角图片,圆角半径
                .into(imageView);//view
    }

    /**
     * 加载gif
     */
    public static void loadGif(Context mContext, String path, ImageView imageView, int radius) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .asGif()
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .into(imageView);//view
    }

    /**
     * 加载图片-指定大小
     */
    public static void loadPictureOverride(Context mContext, String path, ImageView imageView, int wight, int height) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.icon_glide_default)//占位符
                .error(R.mipmap.icon_glide_default)//错误符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
                .override(wight, height)
                .priority(Priority.HIGH);

        Glide.with(mContext)//初始化
                .load(path)//图片地址
                .thumbnail(0.2f)//缩略图
                .transition(withCrossFade())//淡入view
                .apply(requestOptions)//支持项
                .into(imageView);//view
    }
}
