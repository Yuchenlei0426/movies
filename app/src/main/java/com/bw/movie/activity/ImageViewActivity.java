package com.bw.movie.activity;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.evenbean.ImagesShow;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.PhotoDraweeView;

public class ImageViewActivity extends Activity {
    private PhotoDraweeView mPhotoView;
    private String mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        initView();
        EventBus.getDefault().register(this);

        initData();
        initEvent();
    }

    @Subscribe(sticky = true)
    public void ShowImages(ImagesShow imagesShow){
        mImages = imagesShow.getImages();
    }
    private void initView() {
        mPhotoView = (PhotoDraweeView) findViewById(R.id.photoView);
    }
    private void initData() {
        if (!TextUtils.isEmpty(mImages)) {
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(mImages);//设置图片url
            controller.setOldController(mPhotoView.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null || mPhotoView == null) {
                        return;
                    }
                    mPhotoView.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            mPhotoView.setController(controller.build());
        } else {
            Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initEvent() {
        //添加点击事件
        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
