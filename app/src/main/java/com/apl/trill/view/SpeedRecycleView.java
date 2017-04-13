package com.apl.trill.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SpeedRecycleView extends RecyclerView{
    private double scale;

    public SpeedRecycleView(Context context) {
        this(context,null);
    }

    public SpeedRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpeedRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setflingScale(double scale){
        this.scale = scale;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY*=scale;
        return super.fling(velocityX, velocityY);
    }
}
