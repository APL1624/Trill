package com.apl.trill.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.VideoView;

/**
 * Created by Administrator on 2017/4/10.
 */

public class FullVideoView extends VideoView {
    public FullVideoView(Context context) {
        this(context,null);
    }

    public FullVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FullVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        WindowManager systemService = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = systemService.getDefaultDisplay().getWidth();
        int height = systemService.getDefaultDisplay().getHeight();
        setMeasuredDimension(width,height);
    }
}
