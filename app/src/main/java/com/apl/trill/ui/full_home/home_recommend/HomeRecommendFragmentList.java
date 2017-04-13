package com.apl.trill.ui.full_home.home_recommend;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.adapter.HomeRecommendLvAdapter;
import com.apl.trill.bean.home_recommed.HomeRecommendBean;
import com.apl.trill.ui.full_home.home_attention.contrant.HomeAttentionFragmentContract;
import com.apl.trill.ui.full_home.home_attention.model.HomeAttentionFragmentModel;
import com.apl.trill.ui.full_home.home_attention.presenter.HomeAttentionFragmentPresenter;
import com.apl.trill.view.SpeedRecycleView;
import com.vittaw.mvplibrary.base.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendFragmentList extends BaseFragment<HomeAttentionFragmentModel, HomeAttentionFragmentPresenter> implements HomeAttentionFragmentContract.View, RecyclerView.OnItemTouchListener {
    private static final String TAG = HomeRecommendFragmentList.class.getSimpleName();
    @BindView(R2.id.full_recommend_lv_list)
    SpeedRecycleView mFullRecommendLv;
    private List<HomeRecommendBean> mData;
    private HomeRecommendLvAdapter mHomeRecommendLvAdapter;
    private LinearLayoutManager layoutManager;
    private int mJudgeHeight;
    private float mDY;
    private boolean isFirst = true;
    private VideoView mCurrentVideo;
    private float mDownY;
    private float mUpY;
    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private int mStateY;
    private View mVideoPlay;
    private View mVideoCover;
    private int lastPosition;
    private int currentPosition=0;


    @Override
    protected int getLayoutId() {
        return R.layout.full_recommend_lv;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mJudgeHeight = mActivity.getWindowManager().getDefaultDisplay().getHeight() / 2;
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HomeRecommendBean homeRecommendBean = new HomeRecommendBean();
            mData.add(homeRecommendBean);
        }
        layoutManager = new LinearLayoutManager(mActivity);

        mFullRecommendLv.setLayoutManager(layoutManager);

        mFullRecommendLv.setflingScale(0.01);
        mHomeRecommendLvAdapter = new HomeRecommendLvAdapter(mData, mActivity);
        mFullRecommendLv.setAdapter(mHomeRecommendLvAdapter);
        mFullRecommendLv.addOnItemTouchListener(this);

        mFullRecommendLv.addOnScrollListener(new RecyclerView.OnScrollListener() {



            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                Log.e(TAG, "first:" + firstVisibleItemPosition + "  last:" + lastVisibleItemPosition);

                mStateY = dy;


                if (isFirst) {
                    mCurrentVideo = (VideoView) layoutManager.findViewByPosition(firstVisibleItemPosition).findViewById(R.id.full_home_ar_detail_pager_video);
                    mVideoPlay = layoutManager.findViewByPosition(firstVisibleItemPosition).findViewById(R.id.full_home_ar_detail_pager_play);
                    mVideoCover = layoutManager.findViewByPosition(firstVisibleItemPosition).findViewById(R.id.full_home_ar_detail_pager_cover);
                    mCurrentVideo.setVideoURI(Uri.parse("android.resource://" + mActivity.getPackageName() + File.separator + R.raw.show_dog));

                   /* mCurrentVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            switch (what) {
                                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                    mCurrentVideo.pause();
                                    mVideoCover.setVisibility(View.VISIBLE);
                                    break;
                                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                    mVideoCover.setVisibility(View.GONE);


                            }

                            return false;

                        }
                    });*/

                    mCurrentVideo.start();
                    mCurrentVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mVideoCover.setVisibility(View.GONE);

                        }
                    });

                    isFirst = false;
                }

            }

            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mStateY > 0) {
                        //上滑
                        if (mDY > mJudgeHeight) {

                            lastPosition = firstVisibleItemPosition;
                            currentPosition = lastVisibleItemPosition;
                            recyclerView.smoothScrollToPosition(currentPosition);

                        } else {

                            lastPosition = firstVisibleItemPosition;
                            currentPosition = firstVisibleItemPosition;
                            recyclerView.smoothScrollToPosition(currentPosition);
                        }
                    } else {
                        //下滑
                        if (mDY > mJudgeHeight) {
                            lastPosition = lastVisibleItemPosition;
                            currentPosition = firstVisibleItemPosition;
                            recyclerView.smoothScrollToPosition(currentPosition);
                        } else {
                            lastPosition = lastVisibleItemPosition;
                            currentPosition =lastVisibleItemPosition;
                            recyclerView.smoothScrollToPosition(currentPosition);
                        }
                    }
                    if (lastPosition!=currentPosition) {
                        View lastView = layoutManager.findViewByPosition(lastPosition);
                        if (lastView != null) {
                            Log.e(TAG,"lastView+++++++++++++++++++++++");
                            VideoView lastVideo = (VideoView) lastView.findViewById(R.id.full_home_ar_detail_pager_video);
                            lastVideo.pause();
                            lastVideo.resume();
                            View lastCover = lastView.findViewById(R.id.full_home_ar_detail_pager_cover);
                            lastCover.setVisibility(View.VISIBLE);
                        }
                        mCurrentVideo = (VideoView) layoutManager.findViewByPosition( currentPosition ).findViewById(R.id.full_home_ar_detail_pager_video);
                        mVideoCover = layoutManager.findViewByPosition( currentPosition ).findViewById(R.id.full_home_ar_detail_pager_cover);
                        mCurrentVideo.setVideoURI(Uri.parse("android.resource://" + mActivity.getPackageName() + File.separator + R.raw.show_dog));
                        mCurrentVideo.start();
                     /*   mCurrentVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                            @Override
                            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                                switch (what) {
                                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                        mCurrentVideo.pause();
                                        mVideoCover.setVisibility(View.VISIBLE);
                                        break;
                                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                        mVideoCover.setVisibility(View.GONE);

                                }

                                return false;

                            }
                        });*/

                        mCurrentVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mVideoCover.setVisibility(View.GONE);

                            }
                        });

                    }

                }

            }
        });

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
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mDownY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mUpY = e.getY();
                mDY = Math.abs(mUpY - mDownY);


                break;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
