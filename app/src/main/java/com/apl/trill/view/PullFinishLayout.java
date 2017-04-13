package com.apl.trill.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.apl.trill.constants.EventConstants;
import com.apl.trill.event.Event;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * scrollTo()  方法就是将一个视图移动到指定位置
 *
 * 此布局内部需嵌套Layout使用,内部使用Scroller + scrollTo + scrollBy实现
 * Scroller 使View平滑滚动 startScroll()
 * View : scrollTo + scrollBy
 *
 * 注意 : 都是移动布局内部的view
 *
 */

public class PullFinishLayout extends LinearLayout {

    private static final String TAG = PullFinishLayout.class.getSimpleName();
    /**
     * Scroller实例
     */
    private Scroller mScroller;

    /**
     * 认为用户是滑动 的距离
     */
    private int mScaledTouchSlop;//24

    private GestureDetectorCompat mGestureDetector;
    private int mHeight;
    private boolean isClose = true;
    private boolean isScroll = false;
    private boolean isFiling = false;

    /**
     * 里面要放的view
     */
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private float mLastY;
    private float mDownY;
    private MotionEvent mLastDownEvent;

    public PullFinishLayout(Context context) {
        this(context, null);
    }

    public PullFinishLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullFinishLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setClickable(true);

        mScroller = new Scroller(context, new LinearInterpolator());//使用线性插值器
        mScroller.setFriction(400);//摩擦

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();

        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

//                if (e1 == null){//DOWN 事件不会传递到onTouchEvent方法里,DOWN 事件被 内层RecyclerView消费了;只会讲MOVE传递过来
//                    e1 = mLastDownEvent;
//                }

                if (Math.abs(e2.getY() - e1.getY()) > mScaledTouchSlop) {
                    isScroll = true;
                    if (distanceY < 0 && getScrollY() <= 0) {//屏蔽上拉事件
                        //认为用户在滑动
                        scrollBy(0, (int) distanceY);
                        Log.e(TAG, "onScroll: ");
                    }
                }

                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //scroll的速度达到,就会调用此方法
//                Log.e(TAG, "onFling: 速度:   " + velocityY);
                if (velocityY > 2000){
                    isFiling = true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//1070498374897高度好大
        mHeight = getHeight();//1800
    }

    private void open() {
        isClose = false;
        int deltaY = getScrollY();//-560
//        Log.e(TAG, "open: " + deltaY);
        mScroller.startScroll(0, deltaY, 0, -deltaY, 250);//+ 向上滑动
        postInvalidate();
    }

    private void close() {
        isClose = true;
        int deltaY = getScrollY();//-1167
//        Log.e(TAG, "close: " + deltaY);
//        scrollBy(0,-(mHeight + deltaY));//- 向下滑动
        mScroller.startScroll(0, deltaY, 0, -(mHeight + deltaY), 250);//duration 300ms
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: " + ev.getY());
        return super.dispatchTouchEvent(ev);
    }

    //判断 到顶端,下拉状态,就截断消息的传播
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mGestureDetector.onTouchEvent(ev);//手势探测器的e1 报空指针错误
                Log.e(TAG, "ACTION_DOWN: " + ev.getY());
                mDownY = ev.getY();// ACTION_DOWN: 764.60175
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE: " + ev.getY() );

                if (ev.getY() - mDownY > 0 && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0){//ACTION_MOVE: 871.1311 下拉状态
                    return true;//截断消息
                }
                //ACTION_DOWN: 1478.2301    ACTION_MOVE: 1462.3452 上拉状态
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP: " + ev.getY());
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //在View里是onAttachToWindow 注册,另外使用sticky
    @Subscribe(sticky = true)
    public void onEvent(Event event){
        switch (event.getWhat()) {
            case EventConstants.ADD_FRAGMENT_RECYCLERVIEW:
                //初始化内部嵌套的RecyclerView
                mRecyclerView = event.getRecyclerView();
                Logger.e("onEvent: ?" + mRecyclerView );
                if (mRecyclerView != null){
                    if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager){
                        mLayoutManager = ((LinearLayoutManager) mRecyclerView.getLayoutManager());
                    }
                }
                break;
        }
    }

    //使用手势探测器
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " );
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isScroll) {
                    isScroll = false;
                    if (isFiling){
                        isFiling = false;
                        close();
                    }else{
                        if (Math.abs(getScrollY()) > mHeight / 2) {
                            close();
                        } else {
                            open();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 此方法在scrollBy时一直执行
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            //在子线程中   使view重新绘制
            if (!mScroller.isFinished()) {
                postInvalidate();
            } else if(isClose){//关闭状态,销毁activity
                if (onScrollFinishedListener != null) {
                    onScrollFinishedListener.onScrollFinished();
                }
            }
        }

    }

    private OnScrollFinishedListener onScrollFinishedListener;

    public void setOnScrollFinishedListener(OnScrollFinishedListener onScrollFinishedListener) {
        this.onScrollFinishedListener = onScrollFinishedListener;
    }

    public static interface OnScrollFinishedListener {
        void onScrollFinished();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: " );
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow: " );
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }


}
