package com.apl.trill.ui.full_add.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class CollectionsRecyclerView extends RecyclerView {

    public CollectionsRecyclerView(Context context) {
        this(context,null);
    }

    public CollectionsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CollectionsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
