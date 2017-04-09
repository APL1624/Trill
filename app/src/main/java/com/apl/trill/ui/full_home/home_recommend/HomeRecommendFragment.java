package com.apl.trill.ui.full_home.home_recommend;

import com.apl.trill.R;
import com.apl.trill.ui.full_home.home_recommend.contrant.HomeRecommendFragmentContract;
import com.apl.trill.ui.full_home.home_recommend.model.HomeRecommendFragmentModel;
import com.apl.trill.ui.full_home.home_recommend.presenter.HomeRecommendFragmentPresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendFragment extends BaseFragment<HomeRecommendFragmentModel,HomeRecommendFragmentPresenter> implements HomeRecommendFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.home_recommend;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);

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
}
