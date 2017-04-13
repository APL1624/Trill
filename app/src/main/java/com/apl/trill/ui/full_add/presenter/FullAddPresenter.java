package com.apl.trill.ui.full_add.presenter;




import com.apl.trill.bean.AddCollectionBeen;
import com.apl.trill.bean.AddMusicBeen;
import com.apl.trill.ui.full_add.contract.FullAddContract;

import java.io.InputStream;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/3.
 */

public class FullAddPresenter extends FullAddContract.Presenter{


    @Override
    public void getCollectionBeen(InputStream os) {
//        mView.onStartLoad();
        mModel.getCollectionBeen(os).subscribe(new Subscriber<AddCollectionBeen>() {
            @Override
            public void onCompleted() {
//                mView.onStopLoad();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause().getMessage());
            }

            @Override
            public void onNext(AddCollectionBeen addCollectionBeen) {
                mView.returnCollectionBeen(addCollectionBeen);
            }
        });
    }

    @Override
    public void getMusicBeen(Map<String, String> map) {
//        mView.onStartLoad();
        mModel.getAddMusicBeen(map).subscribe(new Subscriber<AddMusicBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause().getMessage());
            }

            @Override
            public void onNext(AddMusicBeen addMusicBeen) {
                mView.returnMusicBeen(addMusicBeen);
            }
        });
//        mView.onStopLoad();
    }
}
