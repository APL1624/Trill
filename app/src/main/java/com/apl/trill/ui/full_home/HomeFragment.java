package com.apl.trill.ui.full_home;

import com.apl.trill.R;
import com.apl.trill.ui.full_home.contrant.HomeFragmentContract;
import com.apl.trill.ui.full_home.model.HomeFragmentModel;
import com.apl.trill.ui.full_home.presenter.HomeFragmentPresenter;
import com.apl.trill.ui.main.MainActivity;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeFragment extends BaseFragment<HomeFragmentModel,HomeFragmentPresenter> implements HomeFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.full_home;
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
