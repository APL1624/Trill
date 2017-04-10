package com.apl.trill.ui.full_home.home_recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.adapter.HomeRecommendDetailViewpagerAdapter;
import com.apl.trill.ui.full_home.home_recommend.contrant.HomeRecommendFragmentContract;
import com.apl.trill.ui.full_home.home_recommend.model.HomeRecommendFragmentModel;
import com.apl.trill.ui.full_home.home_recommend.presenter.HomeRecommendFragmentPresenter;
import com.apl.trill.ui.full_home.home_recommend_detail.HomeRecommendDetailFragment;
import com.apl.trill.view.VerticalViewPager;
import com.vittaw.mvplibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendFragment extends BaseFragment<HomeRecommendFragmentModel, HomeRecommendFragmentPresenter> implements HomeRecommendFragmentContract.View {
    @BindView(R2.id.home_recommend_vertical_vp)
    VerticalViewPager mHomeRecommendVerticalVp;
    private FragmentActivity mFragmentActivity;
    private List<Fragment> mHomeRecommendDetailLv;


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
        mFragmentActivity = (FragmentActivity) mActivity;


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
        mHomeRecommendDetailLv=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            mHomeRecommendDetailLv.add(new HomeRecommendDetailFragment());

        }
        HomeRecommendDetailViewpagerAdapter detailViewPagerAdapter = new HomeRecommendDetailViewpagerAdapter(mFragmentActivity.getSupportFragmentManager(), mHomeRecommendDetailLv);
        mHomeRecommendVerticalVp.setAdapter(detailViewPagerAdapter);


    }
}
