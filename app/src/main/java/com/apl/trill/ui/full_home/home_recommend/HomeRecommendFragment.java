package com.apl.trill.ui.full_home.home_recommend;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.adapter.CryDetailViewpagerAdapter;

import com.apl.trill.ui.full_home.home_recommend.contrant.HomeRecommendFragmentContract;
import com.apl.trill.ui.full_home.home_recommend.model.HomeRecommendFragmentModel;
import com.apl.trill.ui.full_home.home_recommend.presenter.HomeRecommendFragmentPresenter;

import com.apl.trill.ui.full_home.home_recommend_detail_pager.DetailPager;
import com.apl.trill.view.VerticalViewPager;
import com.vittaw.mvplibrary.base.BaseFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendFragment extends BaseFragment<HomeRecommendFragmentModel, HomeRecommendFragmentPresenter> implements HomeRecommendFragmentContract.View, View.OnTouchListener, ViewPager.OnPageChangeListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {
    private static final String TAG = HomeRecommendFragment.class.getSimpleName();
    @BindView(R2.id.home_recommend_vertical_vp)
    VerticalViewPager mHomeRecommendVerticalVp;

    private List<DetailPager> mHomeRecommendDetailLv;
    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;
    private float mUpX;
    private float mUpY;
    private int mExchangeFlag = 0;
    private int mState = 0;
    private SurfaceView mDetailPagerVideo;
    private MediaPlayer mMediaPlayer;
    private View mDetailPagerCover;
    private View mVideoPlay;
    private int mCurrentPosition;
    private SurfaceHolder mHolder;
    private int mCurrentItem;


    @Override
    protected int getLayoutId() {
        return R.layout.home_recommend;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);

    }

    @Override
    protected void initView() {


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeRecommendDetailLv = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            mHomeRecommendDetailLv.add(new DetailPager(mActivity));

        }


        CryDetailViewpagerAdapter cryDetailViewpagerAdapter = new CryDetailViewpagerAdapter(mHomeRecommendDetailLv);

        mHomeRecommendVerticalVp.setAdapter(cryDetailViewpagerAdapter);
        mHomeRecommendVerticalVp.addOnPageChangeListener(this);
        mHomeRecommendVerticalVp.setOnTouchListener(this);
        mHomeRecommendVerticalVp.setOffscreenPageLimit(3);


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.home_recommend_vertical_vp:
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = motionEvent.getX();
                        mDownY = motionEvent.getY();
                        Log.e(TAG, "down:" + mDownX + ":" + mDownY);

                        break;

                    case MotionEvent.ACTION_MOVE:
                        mMoveX = motionEvent.getX();
                        mMoveY = motionEvent.getY();
                        Log.e(TAG, "Move:" + mMoveX + ":" + mMoveY);
                        break;

                    case MotionEvent.ACTION_UP:
                        mUpX = motionEvent.getX();
                        mUpY = motionEvent.getY();
                        Log.e(TAG, "UP:" + mUpX + ":" + mUpY);
                        break;
                }
                if (Math.abs(mUpX - mDownX) < 1 && Math.abs(mUpY - mDownY) < 1) {
                    if (mExchangeFlag == 0) {
                        mMediaPlayer.pause();
                        mCurrentPosition = mMediaPlayer.getCurrentPosition();
                        mVideoPlay.setVisibility(View.VISIBLE);
                        mExchangeFlag = 1;
                    } else {
                        mMediaPlayer.seekTo(mCurrentPosition);
                        mMediaPlayer.start();
                        mVideoPlay.setVisibility(View.GONE);
                        mExchangeFlag = 0;
                    }


                }
                break;
        }


        return false;
    }

    public void initViewPagerView() {

        Log.e(TAG, "SCoLL:" + mCurrentItem);
        View currentView = mHomeRecommendVerticalVp.getChildAt(mCurrentItem);
        mDetailPagerVideo = (SurfaceView) currentView.findViewById(R.id.full_home_ar_detail_pager_video);
        mDetailPagerCover = currentView.findViewById(R.id.full_home_ar_detail_pager_cover);
        mVideoPlay = currentView.findViewById(R.id.full_home_ar_detail_pager_play);
        mDetailPagerCover.setVisibility(View.GONE);
        mDetailPagerVideo.setVisibility(View.VISIBLE);


        mHolder = mDetailPagerVideo.getHolder();
        mHolder.addCallback(this);



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCurrentItem = position;
        if (mState == 0) {
            initViewPagerView();
        }

    }

    @Override
    public void onPageSelected(int position) {
        Log.e(TAG, "Selected:" + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            mState = 0;
            Log.e(TAG, "静止");
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            initViewPagerView();
        } else {
            mState = 1;
            Log.e(TAG, "移动");
        }


    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setDisplay(mHolder);
        try {
            mMediaPlayer.setDataSource(mActivity, Uri.parse("android.resource://" + mActivity.getPackageName() + File.separator + R.raw.show_dog));
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
