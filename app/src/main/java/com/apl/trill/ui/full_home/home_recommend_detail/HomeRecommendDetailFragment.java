package com.apl.trill.ui.full_home.home_recommend_detail;

import android.net.Uri;
import android.widget.VideoView;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.ui.full_home.home_recommend_detail.contrant.HomeRecommendDetailContract;
import com.apl.trill.ui.full_home.home_recommend_detail.model.HomeRecommendDetailModel;
import com.apl.trill.ui.full_home.home_recommend_detail.presenter.HomeRecommendDetailPresenter;
import com.apl.trill.view.FullVideoView;
import com.vittaw.mvplibrary.base.BaseFragment;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendDetailFragment extends BaseFragment<HomeRecommendDetailModel,HomeRecommendDetailPresenter> implements HomeRecommendDetailContract.View {
    @BindView(R2.id.full_home_ar_detail_pager_video)
    FullVideoView mFullHomeArDetailPagerVideo;
    @Override
    protected int getLayoutId() {
        return R.layout.full_home_ar_detial_pager;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);

    }

    @Override
    protected void initView() {
        playVideo();

    }

    private void playVideo() {
        mFullHomeArDetailPagerVideo.setVideoURI(Uri.parse("android.resource://"+mActivity.getPackageName()+ File.separator+R.raw.show));
        mFullHomeArDetailPagerVideo.start();

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
}
