package com.apl.trill.ui.full_mine;

import com.apl.trill.R;
import com.apl.trill.ui.full_mine.contrant.MineFragmentContract;
import com.apl.trill.ui.full_mine.model.MineFragmentModel;
import com.apl.trill.ui.full_mine.presenter.MineFragmentPresenter;
import com.apl.trill.ui.main.MainActivity;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MineFragment extends BaseFragment<MineFragmentModel,MineFragmentPresenter> implements MineFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.full_mine;
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
