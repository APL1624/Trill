package com.apl.trill.ui.full_msg;

import com.apl.trill.R;
import com.apl.trill.ui.full_msg.contrant.MsgFragmentContract;
import com.apl.trill.ui.full_msg.model.MsgFragmentModel;
import com.apl.trill.ui.full_msg.presenter.MsgFragmentPresenter;
import com.apl.trill.ui.main.MainActivity;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MsgFragment extends BaseFragment<MsgFragmentModel,MsgFragmentPresenter> implements MsgFragmentContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.full_msg;
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
