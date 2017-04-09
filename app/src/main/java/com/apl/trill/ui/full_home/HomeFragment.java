package com.apl.trill.ui.full_home;


import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.ui.full_home.contrant.HomeFragmentContract;
import com.apl.trill.ui.full_home.home_attention.HomeAttentionFragment;
import com.apl.trill.ui.full_home.home_fresh.HomeFreshFragment;
import com.apl.trill.ui.full_home.home_recommend.HomeRecommendFragment;
import com.apl.trill.ui.full_home.model.HomeFragmentModel;
import com.apl.trill.ui.full_home.presenter.HomeFragmentPresenter;

import com.apl.trill.util.DensityUtils;
import com.apl.trill.util.SwitchFragment;
import com.vittaw.mvplibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * fqc,cry,cry
 */

public class HomeFragment extends BaseFragment<HomeFragmentModel, HomeFragmentPresenter> implements HomeFragmentContract.View, RadioGroup.OnCheckedChangeListener {
    @BindView(R2.id.full_home_tab_move_line)
    View mFullHomeTabMovieLine;
    @BindView(R2.id.full_home_tab_container)
    RadioGroup mFullHomeTabContainer;
    @BindView(R2.id.full_home_tab_recommend)
    RadioButton mFullHomeTabRecommend;
    BaseFragment mHomeShowFragment;

    private int mCheckedPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.full_home;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        mCheckedPosition=0;
        mFullHomeTabContainer.setOnCheckedChangeListener(this);

        mFullHomeTabRecommend.setChecked(true);


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
        switch (i) {
            case R.id.full_home_tab_attention:
                mCheckedPosition=0;
                moveLineMove(mCheckedPosition);

                mHomeShowFragment=SwitchFragment.switchFragment(HomeAttentionFragment.class,getActivity(),mHomeShowFragment,R.id.full_home_content);

                break;
            case R.id.full_home_tab_recommend:
                mCheckedPosition=1;
                moveLineMove(mCheckedPosition);

                mHomeShowFragment=SwitchFragment.switchFragment(HomeRecommendFragment.class,getActivity(),mHomeShowFragment,R.id.full_home_content);
                break;
            case R.id.full_home_tab_fresh:
                mCheckedPosition=2;
                moveLineMove(mCheckedPosition);

                mHomeShowFragment=SwitchFragment.switchFragment(HomeFreshFragment.class,getActivity(),mHomeShowFragment,R.id.full_home_content);
                break;
        }
    }

    private void moveLineMove( int checkedPosition) {
        mFullHomeTabMovieLine.setTranslationX(checkedPosition* DensityUtils.dp2px(getActivity(),60));
    }
}
