package com.vittaw.mvplibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vittaw.mvplibrary.utils.TypeUtil;

import butterknife.ButterKnife;



public abstract class BaseFragment<M extends BaseModel,P extends BasePresenter> extends Fragment {

    public M mModel;

    public P mPresenter;

    protected View layout;
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null){
            layout = inflater.inflate(getLayoutId(),container,false);
        }
        //View 注入
        ButterKnife.bind(this,layout);
        //初始化presenter
        mModel = TypeUtil.getObject(this,0);
        mPresenter = TypeUtil.getObject(this,1);

        initPresenter();
        //初始化View
        initView();
        return layout;
    }


    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initView();
}
