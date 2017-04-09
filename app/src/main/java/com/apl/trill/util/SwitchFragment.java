package com.apl.trill.util;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vittaw.mvplibrary.base.BaseFragment;


/**
 * Created by Administrator on 2017/2/17.
 */

public class SwitchFragment {
    public static BaseFragment switchFragment(Class< ? extends BaseFragment>cls, FragmentActivity fragmentActivity,
                                              BaseFragment mShowFragment, int containerViewId){
       //获取管理器
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
       //开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //开始隐藏
        if (mShowFragment!=null) {
            transaction.hide(mShowFragment);
        }
        //从缓存中读取Fragment
        mShowFragment= (BaseFragment) fragmentManager.findFragmentByTag(cls.getName());
        if (mShowFragment!=null) {
            transaction.show(mShowFragment);
        }else {
            try {
                mShowFragment=cls.newInstance();
                transaction.add(containerViewId,mShowFragment,cls.getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
        return mShowFragment;
    }
}
