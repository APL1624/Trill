package com.apl.trill.ui.full_add.model;



import com.apl.trill.api.Api;
import com.apl.trill.bean.AddCollectionBeen;
import com.apl.trill.bean.AddMusicBeen;
import com.apl.trill.bean.SplashBean;
import com.apl.trill.ui.full_add.contract.FullAddContract;
import com.google.gson.Gson;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;


/**
 *
 */

public class FullAddModel implements FullAddContract.Model {


    @Override
    public Observable<AddCollectionBeen> getCollectionBeen(InputStream os) {
        //json串到 JavaBeen的转换
        return Observable.just(os).map(new Func1<InputStream, AddCollectionBeen>() {
            @Override
            public AddCollectionBeen call(InputStream inputStream) {
                Gson gson = new Gson();
                AddCollectionBeen addCollectionBeen = gson.fromJson(new InputStreamReader(inputStream), AddCollectionBeen.class);
                return addCollectionBeen;
            }
        }).compose(new AndroidIOToMain.IOToMainTransformer<AddCollectionBeen>());
    }

    @Override
    public Observable<AddMusicBeen> getAddMusicBeen(Map<String, String> map) {
        return Api.getApiService().getAddMusicBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<AddMusicBeen>());
    }
}
