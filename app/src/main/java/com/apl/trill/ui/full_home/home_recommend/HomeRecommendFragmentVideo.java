package com.apl.trill.ui.full_home.home_recommend;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendFragmentVideo extends BaseFragment<HomeRecommendFragmentModel, HomeRecommendFragmentPresenter> implements HomeRecommendFragmentContract.View, View.OnTouchListener, ViewPager.OnPageChangeListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final String TAG = HomeRecommendFragmentVideo.class.getSimpleName();
    @BindView(R2.id.home_recommend_vertical_vp)
    VerticalViewPager mHomeRecommendVerticalVp;

    private List<DetailPager> mHomeRecommendDetailLv;
    private List<VideoView> mDetailPagerVideo;
    private List<ImageView> mCopyView;
    private List<ImageView> mVideoPlay;
    private Map<Integer, Integer> mCurrentPosition;
    private CryDetailViewpagerAdapter mDetailViewpagerAdapter;
    private int currentItem=0;
    private int lastItem=0;

    private float mDownX;
    private float mDownY;
    private float mUpX;
    private float mUpY;

    private int mState = 0;



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
        mDetailPagerVideo = new ArrayList<>();
        mCopyView = new ArrayList<>();
        mVideoPlay=new ArrayList<>();

        mCurrentPosition = new HashMap<>();


        for (int i = 0; i < 10; i++) {
            DetailPager detailPager = new DetailPager(mActivity);
            VideoView video = (VideoView) detailPager.getRootView().findViewById(R.id.full_home_ar_detail_pager_video);
            ImageView cryCover = (ImageView) detailPager.getRootView().findViewById(R.id.full_home_ar_detail_pager_cover);
            ImageView play = (ImageView) detailPager.getRootView().findViewById(R.id.full_home_ar_detail_pager_play);
            video.setVideoURI(Uri.parse("android.resource://" + mActivity.getPackageName() + File.separator + R.raw.show_dog));
            setListener(video);
            mHomeRecommendDetailLv.add(detailPager);
            mDetailPagerVideo.add(video);
            mCopyView.add(cryCover);
            mVideoPlay.add(play);
            mCurrentPosition.put(i,0);


        }
        mDetailViewpagerAdapter = new CryDetailViewpagerAdapter(mHomeRecommendDetailLv);
        mHomeRecommendVerticalVp.setAdapter(mDetailViewpagerAdapter);
        mHomeRecommendVerticalVp.addOnPageChangeListener(this);
        mHomeRecommendVerticalVp.setOnTouchListener(this);



    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.home_recommend_vertical_vp:
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = motionEvent.getX();
                        mDownY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_UP:
                        mUpX = motionEvent.getX();
                        mUpY = motionEvent.getY();

                        break;
                }
                if (Math.abs(mUpX - mDownX) < 1 && Math.abs(mUpY - mDownY) < 1) {
                    if (mDetailPagerVideo.get(currentItem).isPlaying()) {

                        mDetailPagerVideo.get(currentItem).pause();
                        mCurrentPosition.put(currentItem,mDetailPagerVideo.get(currentItem).getCurrentPosition());
                        mVideoPlay.get(currentItem).setVisibility(View.VISIBLE);
                    }else {

                        mDetailPagerVideo.get(currentItem).seekTo(mCurrentPosition.get(currentItem));
                        mDetailPagerVideo.get(currentItem).start();
                        mVideoPlay.get(currentItem).setVisibility(View.GONE);
                    }


                }
                break;
        }


        return false;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mState == 0) {
            if (mDetailPagerVideo.get(0)!=null) {
                mDetailPagerVideo.get(0).start();


            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        Log.e(TAG, "Selected:" + position);
        lastItem=currentItem;
        currentItem=position;
        if (mDetailPagerVideo.get(lastItem)!=null) {
            if (mDetailPagerVideo.get(lastItem).isPlaying()) {
                //TODO
                mDetailPagerVideo.get(lastItem).pause();
                mCopyView.get(lastItem).setVisibility(
                        View.VISIBLE);
                mVideoPlay.get(lastItem).setVisibility(View.GONE);

            }
            mCurrentPosition.put(lastItem,0);



        }
        if (mDetailPagerVideo.get(currentItem)!=null) {
            mCopyView.get(currentItem).setVisibility(
                    View.VISIBLE);
            mVideoPlay.get(currentItem).setVisibility(View.GONE);
            mDetailPagerVideo.get(currentItem).seekTo(0);
            mDetailPagerVideo.get(currentItem).start();

            mCopyView.get(currentItem).setVisibility(
                    View.GONE);

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            mState = 0;
            Log.e(TAG, "静止");
        } else {
            mState = 1;
            Log.e(TAG, "移动");
        }


    }






    public void setListener(VideoView listener) {

        listener.setOnCompletionListener(this);
        listener.setOnErrorListener(this);
        listener.setOnPreparedListener(this);
     //   listener.setZOrderOnTop(true);
       // listener.setZOrderMediaOverlay(true);
    }



    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mDetailPagerVideo.get(currentItem)!=null) {

            mCurrentPosition.put(currentItem,0);
            mDetailPagerVideo.get(currentItem).resume();
            mDetailPagerVideo.get(currentItem).start();
        }

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDetailPagerVideo.get(currentItem)!=null) {
            mDetailPagerVideo.get(currentItem).stopPlayback();
            mDetailPagerVideo.get(currentItem).suspend();
            clearList();
        }
    }

    private void clearList() {
        mDetailPagerVideo.clear();
        mCopyView.clear();
        mCurrentPosition.clear();
        mVideoPlay.clear();

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mCopyView.get(currentItem).setVisibility(View.GONE);
        mVideoPlay.get(currentItem).setVisibility(View.GONE);
    }
}
