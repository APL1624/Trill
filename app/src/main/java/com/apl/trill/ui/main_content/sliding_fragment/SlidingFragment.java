package com.apl.trill.ui.main_content.sliding_fragment;

import com.apl.trill.R;
import com.apl.trill.ui.main_content.sliding_fragment.contrant.SlidingFragmentContract;
import com.apl.trill.ui.main_content.sliding_fragment.model.SlidingFragmentModel;
import com.apl.trill.ui.main_content.sliding_fragment.presenter.SlidingFragmentPresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class SlidingFragment  extends BaseFragment<SlidingFragmentModel,SlidingFragmentPresenter> implements SlidingFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sliding;
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
