package com.apl.trill.ui.full_add.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class CollectionsDecorate extends RecyclerView.ItemDecoration {

    /**
     *  在每一个item插入时设置偏移的像素数量,相当于padding或margin,默认是0偏移量
     * @param outRect 在返回之前,设置item的四个方向的属性
     * @param view 如果你需要得到额外的数据,可以通过getChildAdapterPosition(View) 来获取position
     * @param parent 这个条目装饰者 装饰的RecyclerView
     * @param state RecyclerView 当前的状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(3,3,3,3);
    }

}
