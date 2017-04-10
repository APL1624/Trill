package com.apl.trill.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class HomeRecommendDetailViewpagerAdapter extends FragmentPagerAdapter{
    List<Fragment>data;

    public HomeRecommendDetailViewpagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data=data;

    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
