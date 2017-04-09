package com.apl.trill.ui.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;


import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.ui.main.MainActivity;
import com.apl.trill.ui.splash.contract.SplashContract;
import com.apl.trill.ui.splash.model.SplashModel;
import com.apl.trill.ui.splash.presenter.SplashPresenter;
import com.bumptech.glide.Glide;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class SplashActivity extends BaseActivity<SplashPresenter,SplashModel>implements SplashContract.View , Handler.Callback{
    private Handler mHandler=new Handler(this);
    @BindView(R2.id.splash_image)
    ImageView mSplshImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        int resourceId = R.mipmap.app_icon;
        Glide.with(this).load(resourceId).bitmapTransform(new CropCircleTransformation(this)).into(mSplshImage);

        mHandler.sendEmptyMessageDelayed(10,1*1000);
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {

    }

    @Override
    public boolean handleMessage(Message message) {
        jump();

        return false;
    }

    private void jump() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
