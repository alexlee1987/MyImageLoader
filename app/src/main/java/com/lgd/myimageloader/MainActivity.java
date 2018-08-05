package com.lgd.myimageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {
    public String mUrl = "http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg";
    @ViewInject(R.id.imgView)
    private ImageView mImageView;
    @ViewInject(R.id.load_glide_btn)
    private Button mLoadWithGlide;
    @ViewInject(R.id.load_imageloader_btn)
    private Button mLoadWithImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        AnnotateUtils.INSTANCE.injectViews(this);
        mLoadWithGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWithGlide();
            }
        });
        mLoadWithImageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWithImageLoader();
            }
        });
    }

    private void loadWithGlide() {
        mImageView.setTag(null);
        // 设置requestOptions：占位图、异常占位图、缓存策略、图片指定大小、图片转换（如图片圆角化、圆形化、模糊化）等等
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background) // 占位图
                .error(R.drawable.ic_launcher_foreground) // 异常占位图
                .skipMemoryCache(true) // Glide的内存缓存功能
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC); // 指定缓存策略
        GlideApp.with(mImageView).load(mUrl)
                .apply(requestOptions)
                .into(mImageView);
    }

    private void loadWithImageLoader() {
        mImageView.setTag(null);
        mImageView.setImageBitmap(null);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new DoubleCache());
        imageLoader.displayImage(mUrl, mImageView);
    }
}
