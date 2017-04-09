package com.apl.trill.ui.full_add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apl.trill.R;
import com.apl.trill.ui.full_add.contract.FullAddContract;
import com.apl.trill.ui.full_add.model.FullAddModel;
import com.apl.trill.ui.full_add.presenter.FullAddPresenter;
import com.vittaw.mvplibrary.base.BaseActivity;

public class FullAddActivity extends BaseActivity<FullAddPresenter,FullAddModel>implements FullAddContract.View{


    @Override
    public int getLayoutId() {
        return R.layout.full_add;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

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
