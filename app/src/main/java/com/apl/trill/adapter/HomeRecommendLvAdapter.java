package com.apl.trill.adapter;

import android.content.Context;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.bean.home_recommed.HomeRecommendBean;
;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/12.
 */

public class HomeRecommendLvAdapter extends RecyclerView.Adapter<HomeRecommendLvAdapter.ViewHolderContent> implements MediaPlayer.OnPreparedListener {
    private List<HomeRecommendBean>mData;
    private Context context;

    public HomeRecommendLvAdapter(List<HomeRecommendBean> mData, Context context) {
        if (mData!=null) {
            this.mData=mData;
        }else {
            this.mData=new ArrayList<>();
        }
        this.context = context;
    }
    public void updateRes(List<HomeRecommendBean>mData){
        if (mData!=null) {
            this.mData.clear();
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<HomeRecommendBean>mData){
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolderContent onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.full_home_ar_detial_pager,parent,false);

        return new ViewHolderContent(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderContent holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public  static  class  ViewHolderContent extends RecyclerView.ViewHolder{

        @BindView(R2.id.full_home_ar_detail_pager_video)
        VideoView videoView;
        public ViewHolderContent(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
