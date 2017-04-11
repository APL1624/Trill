package com.apl.trill.ui.full_home.home_recommend_detail_pager;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.apl.trill.R;

import java.io.File;


/**
 * Created by Administrator on 2017/4/11.
 */

public class DetailPager {
    private static final String TAG =DetailPager.class.getSimpleName() ;
    private Activity mActivity;
    private  View mRootView;


    public DetailPager(Activity mActivity) {
        this.mActivity = mActivity;
        mRootView=View.inflate(mActivity, R.layout.full_home_ar_detial_pager,null);
    }
    public View getRootView(){
        return mRootView;
    }
    public void initData(){
      //  Log.e(TAG,"fff");

    }

}
