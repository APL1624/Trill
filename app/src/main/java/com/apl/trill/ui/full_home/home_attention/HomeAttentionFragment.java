package com.apl.trill.ui.full_home.home_attention;

import com.apl.trill.R;
import com.apl.trill.ui.full_home.home_attention.contrant.HomeAttentionFragmentContract;
import com.apl.trill.ui.full_home.home_attention.model.HomeAttentionFragmentModel;
import com.apl.trill.ui.full_home.home_attention.presenter.HomeAttentionFragmentPresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeAttentionFragment extends BaseFragment<HomeAttentionFragmentModel,HomeAttentionFragmentPresenter> implements HomeAttentionFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.home_attention;
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
