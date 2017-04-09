package com.apl.trill.ui.full_add.model;



import com.apl.trill.bean.SplashBean;
import com.apl.trill.ui.full_add.contract.FullAddContract;

import java.util.Map;

import rx.Observable;


/**
 *
 */

public class FullAddModel implements FullAddContract.Model {

    @Override
    public Observable<SplashBean> getSplashBean(Map<String, String> map) {

        return null;
    }
}
