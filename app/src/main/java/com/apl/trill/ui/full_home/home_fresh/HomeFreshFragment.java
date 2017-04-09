package com.apl.trill.ui.full_home.home_fresh;

import com.apl.trill.R;
import com.apl.trill.ui.full_home.home_fresh.contrant.HomeFreshFragmentContract;
import com.apl.trill.ui.full_home.home_fresh.model.HomeFreshFragmentModel;
import com.apl.trill.ui.full_home.home_fresh.presenter.HomeFreshFragmentPresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeFreshFragment extends BaseFragment<HomeFreshFragmentModel,HomeFreshFragmentPresenter> implements HomeFreshFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.home_fresh;
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
