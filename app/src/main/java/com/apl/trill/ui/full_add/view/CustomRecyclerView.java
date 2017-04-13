package com.apl.trill.ui.full_add.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;



public class CustomRecyclerView extends RecyclerView {

    private static final  String TAG = CustomRecyclerView.class.getSimpleName();

    private LinearLayoutManager mLayoutManager;

    public CustomRecyclerView(Context context) {
        this(context,null);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mLayoutManager = ((LinearLayoutManager) getLayoutManager());

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: " + ev.getY());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.e(TAG, "onInterceptTouchEvent: " );
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION_DOWN: " + e.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE: " + e.getY() );
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP: " + e.getY());
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.e(TAG, "onTouchEvent: " );
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION_DOWN: " + e.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE: " + e.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP: " + e.getY());
                break;
        }
        return super.onTouchEvent(e);
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        Log.e(TAG, "onTouchEvent: " + e.getY() );
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG, "ACTION_DOWN: " + e.getY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e(TAG, "ACTION_MOVE: " + e.getY());
//                if (e.getY() - mLastY > 0) {//下拉
//                    isDown = true;
//                    Log.e(TAG, "onTouchEvent: 下拉" );
//                }else{
//                    isDown = false;
//                    Log.e(TAG, "onTouchEvent: 上拉" );
//                }
//                mLastY = e.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e(TAG, "ACTION_UP: " + e.getY());
//                if (isDown && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0){
//                    isClose = true;
//                }else{
//                    isClose = false;
//                }
//                break;
//        }
//        Log.e(TAG, "onTouchEvent: isClose---- " + isClose );
//        return isClose ? false : super.onTouchEvent(e);//只要return false了,以后就再也收不到事件了
//    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        Log.e(TAG, "onTouchEvent: " + getScrollY() );//0
//
        //如果是关闭的话,这个事件RecyclerView不消费,交给顶部的activity去消费
//        return isClose ? false : super.onTouchEvent(e);
//    }


}
