package com.apl.trill.ui.full_add;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.ui.full_add.contract.FullAddContract;
import com.apl.trill.ui.full_add.fragment.AddFragment;
import com.apl.trill.ui.full_add.model.FullAddModel;
import com.apl.trill.ui.full_add.presenter.FullAddPresenter;
import com.apl.trill.util.SwitchFragment;
import com.apl.trill.view.PullFinishLayout;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;

public class FullAddActivity extends BaseActivity{


    private static final String TAG = FullAddActivity.class.getSimpleName();

    @BindView(R2.id.pull_finish_layout)
    PullFinishLayout pullFinishLayout;

    @Override
    public int getLayoutId() {
        return R.layout.full_add;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //设置沉浸式状态栏 显示出来
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.STATUS_BAR_VISIBLE;
            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        pullFinishLayout.setOnScrollFinishedListener(new PullFinishLayout.OnScrollFinishedListener() {
            @Override
            public void onScrollFinished() {
                finish();
                overridePendingTransition(0, R.anim.add_exit);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.add_container,new AddFragment());
        transaction.commit();

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(0, R.anim.add_exit);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " );
        return super.onTouchEvent(event);
    }
}
