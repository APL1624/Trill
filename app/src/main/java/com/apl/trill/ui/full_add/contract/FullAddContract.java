package com.apl.trill.ui.full_add.contract;


import com.apl.trill.bean.AddCollectionBeen;
import com.apl.trill.bean.AddMusicBeen;
import com.apl.trill.bean.SplashBean;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.io.InputStream;
import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/3.
 */

public interface FullAddContract {
    interface Model extends BaseModel {
        Observable<AddCollectionBeen> getCollectionBeen(InputStream os);

        Observable<AddMusicBeen> getAddMusicBeen(Map<String,String> map);

    }

    interface View extends BaseView {

        void returnCollectionBeen(AddCollectionBeen addCollectionBeen);

        void returnMusicBeen(AddMusicBeen addMusicBeen);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getCollectionBeen(InputStream os);

        public abstract void getMusicBeen(Map<String,String> map);
    }
}
