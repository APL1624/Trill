package com.apl.trill.api;

import com.apl.trill.bean.AddMusicBeen;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/3.
 */

public interface ApiService {

    /**添加页面
     * https://api.amemv.com
     * /aweme/v1/hot/music/
     * ?cursor=0&count=20&iid=9281644471
     */
    @GET("/aweme/v1/hot/music/")
    Observable<AddMusicBeen> getAddMusicBeen(@QueryMap Map<String,String> map);

}
