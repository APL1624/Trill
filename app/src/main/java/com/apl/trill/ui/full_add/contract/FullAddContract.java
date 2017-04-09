package com.apl.trill.ui.full_add.contract;


import com.apl.trill.bean.SplashBean;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/3.
 */

public interface FullAddContract {
    interface  Model extends BaseModel{
        Observable<SplashBean>getSplashBean(Map<String, String> map);
    }
     interface View extends BaseView {

    }
    abstract  class Presenter extends BasePresenter<Model,View>
    {
        public abstract void getSplashBeen(Map<String ,String > map);
    }
}
