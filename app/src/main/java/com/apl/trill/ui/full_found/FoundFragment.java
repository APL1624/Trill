package com.apl.trill.ui.full_found;

import com.apl.trill.R;
import com.apl.trill.ui.full_found.contrant.FoundFragmentContract;
import com.apl.trill.ui.full_found.model.FoundFragmentModel;
import com.apl.trill.ui.full_found.presenter.FoundFragmentPresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class FoundFragment extends BaseFragment<FoundFragmentModel,FoundFragmentPresenter> implements FoundFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.full_found;
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
