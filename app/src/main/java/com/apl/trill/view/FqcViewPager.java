package com.apl.trill.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/4/9.
 */

public class FqcViewPager extends ViewPager {
    private  boolean mNoScroll=true;
    public FqcViewPager(Context context) {
        super(context);
    }

    public FqcViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setNoScroll(boolean noScroll){
         mNoScroll=noScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (mNoScroll) {
            return  false;
        }else {
            return super.onInterceptTouchEvent(ev);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (mNoScroll) {
            return false;
        }else {
            return super.onTouchEvent(ev);
        }
    }
}
