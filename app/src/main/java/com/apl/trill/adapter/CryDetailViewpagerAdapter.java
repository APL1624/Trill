package com.apl.trill.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.apl.trill.ui.full_home.home_recommend_detail_pager.DetailPager;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class CryDetailViewpagerAdapter extends PagerAdapter{
    List<DetailPager>mData;

    public CryDetailViewpagerAdapter(List<DetailPager> mData) {
        this.mData = mData;
    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DetailPager detailPager = mData.get(position);
        container.addView(detailPager.getRootView());
        detailPager.initData();
        return detailPager.getRootView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
