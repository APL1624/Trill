package com.apl.trill.ui.main_content;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.ui.full_add.FullAddActivity;
import com.apl.trill.ui.full_found.FoundFragment;
import com.apl.trill.ui.full_home.HomeFragment;
import com.apl.trill.ui.full_mine.MineFragment;
import com.apl.trill.ui.full_msg.MsgFragment;
import com.apl.trill.ui.main.MainActivity;
import com.apl.trill.ui.main.contract.MainContract;
import com.apl.trill.ui.main_content.contrant.MainFragmentContract;
import com.apl.trill.ui.main_content.model.MainFragmentModel;
import com.apl.trill.ui.main_content.presenter.MainFragmentPresenter;
import com.apl.trill.util.SwitchFragment;
import com.vittaw.mvplibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MainFragment extends BaseFragment<MainFragmentModel,MainFragmentPresenter>implements MainFragmentContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R2.id.fragment_main_bottom_container)
    RadioGroup mFragmentMainBottomContainer;
    @BindView(R2.id.fragment_main_bottom_home)
    RadioButton mFragmentMainBottomHome;
    private BaseFragment mShowFragment;
    @BindView(R2.id.fragment_main_bottom_add)
    Button mFragmentMainBottomAdd;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mFragmentMainBottomContainer.setOnCheckedChangeListener(this);
        mFragmentMainBottomHome.setChecked(true);
        mFragmentMainBottomAdd.setOnClickListener(this);


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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        MainActivity mainActivity= (MainActivity) mActivity;
        switch (i) {
            case R.id.fragment_main_bottom_home:

                mainActivity.getFqcViewPager().setNoScroll(false);
                mShowFragment= SwitchFragment.switchFragment(HomeFragment.class,getActivity(),mShowFragment,R.id.fragment_main_content);
                break;
            case R.id.fragment_main_bottom_found:

                mainActivity.getFqcViewPager().setNoScroll(true);
                mShowFragment= SwitchFragment.switchFragment(FoundFragment.class,getActivity(),mShowFragment,R.id.fragment_main_content);
                break;
            case R.id.fragment_main_bottom_msg:
                mainActivity.getFqcViewPager().setNoScroll(true);
                mShowFragment= SwitchFragment.switchFragment(MsgFragment.class,getActivity(),mShowFragment,R.id.fragment_main_content);
                break;
            case R.id.fragment_main_bottom_mine:
                mainActivity.getFqcViewPager().setNoScroll(true);
                mShowFragment= SwitchFragment.switchFragment(MineFragment.class,getActivity(),mShowFragment,R.id.fragment_main_content);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           case  R.id.fragment_main_bottom_add:
               startActivity(new Intent(getActivity(),FullAddActivity.class));
               getActivity().overridePendingTransition(R.anim.add_enter,0);
               break;
        }
    }
}
