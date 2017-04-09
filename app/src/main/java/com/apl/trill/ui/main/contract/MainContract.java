package com.apl.trill.ui.main.contract;

import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

/**
 * Created by Administrator on 2017/4/3.
 */

public interface MainContract {

    interface  Model extends BaseModel{

    }
     interface View extends BaseView {

    }
    abstract  class Presenter extends BasePresenter<Model,View>
    {

    }
}
