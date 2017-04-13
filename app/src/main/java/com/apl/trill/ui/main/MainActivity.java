package com.apl.trill.ui.main;



import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.KeyEvent;
import android.widget.Toast;


import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.adapter.MainViewpagerAdapter;
import com.apl.trill.ui.main.contract.MainContract;
import com.apl.trill.ui.main.model.MainModel;
import com.apl.trill.ui.main.presenter.MainPresenter;


import com.apl.trill.ui.main_content.MainFragment;
import com.apl.trill.ui.main_content.sliding_fragment.SlidingFragment;
import com.apl.trill.view.FqcViewPager;
import com.vittaw.mvplibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainContract.View{
    @BindView(R2.id.main_content)
    FqcViewPager mMainVp;
    private long firstTime=0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        MainViewpagerAdapter viewpagerAdapter = new MainViewpagerAdapter(getSupportFragmentManager(), getData());
        mMainVp.setAdapter(viewpagerAdapter);
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

    public List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new MainFragment());
        data.add(new SlidingFragment());
        return data;
    }
    public FqcViewPager getFqcViewPager(){
        return mMainVp;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
           case  KeyEvent.KEYCODE_BACK:
               long secondTime = System.currentTimeMillis();
               if (secondTime-firstTime>2000) {
                   Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                   firstTime=secondTime;
                   return true;
               }else {
                   System.exit(0);
               }
               break;
        }
        return super.onKeyUp(keyCode,event);
    }

}
