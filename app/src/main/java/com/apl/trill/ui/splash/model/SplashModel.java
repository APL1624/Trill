package com.apl.trill.ui.splash.model;



import com.apl.trill.bean.SplashBean;
import com.apl.trill.ui.splash.contract.SplashContract;

import java.util.Map;

import rx.Observable;


/**
 *
 */

public class SplashModel implements SplashContract.Model {

    @Override
    public Observable<SplashBean> getSplashBean(Map<String, String> map) {

        return null;
    }
}
