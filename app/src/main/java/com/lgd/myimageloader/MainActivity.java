package com.lgd.myimageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public String mUrl = "http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        final ImageView imgView = findViewById(R.id.imgView);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new DoubleCache());
        imageLoader.displayImage(mUrl, imgView);
    }
}
