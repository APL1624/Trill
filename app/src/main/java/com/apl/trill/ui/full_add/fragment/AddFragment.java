package com.apl.trill.ui.full_add.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.bean.AddCollectionBeen;
import com.apl.trill.bean.AddMusicBeen;
import com.apl.trill.constants.EventConstants;
import com.apl.trill.event.Event;
import com.apl.trill.ui.full_add.adapter.AddRecyclerAdapter;
import com.apl.trill.ui.full_add.contract.FullAddContract;
import com.apl.trill.ui.full_add.model.FullAddModel;
import com.apl.trill.ui.full_add.presenter.FullAddPresenter;
import com.apl.trill.ui.full_add.view.CustomRecyclerView;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


public class AddFragment extends BaseFragment<FullAddModel ,FullAddPresenter > implements FullAddContract.View {

    public static final String TAG = AddFragment.class.getSimpleName();

    @BindView(R2.id.add_music_start_now)
    TextView mStartNow;

    @BindView(R2.id.add_edit_text)
    EditText mSearch;

    @BindView(R2.id.add_recycler_view)
    CustomRecyclerView mRecyclerView;
    private AddRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean isDown = true;
    private boolean isClose = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mSearch.setFocusable(false);
        mAdapter = new AddRecyclerAdapter(getActivity(),null);
        mRecyclerView.setAdapter(mAdapter);

        deliverRecyclerViewToPullFinishLayout();

        mLayoutManager = ((LinearLayoutManager) mRecyclerView.getLayoutManager());

        try {
            InputStream os = getActivity().getAssets().open("add_collection.json");
            mPresenter.getCollectionBeen(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPresenter.getMusicBeen(createMap());
    }

    private void deliverRecyclerViewToPullFinishLayout() {
        Logger.e("传递recclerView");
        Event event = new Event(EventConstants.ADD_FRAGMENT_RECYCLERVIEW);
        event.setRecyclerView(mRecyclerView);
        EventBus.getDefault().postSticky(event);
    }

    private void interceptTouchEvent() {
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        Log.e(TAG, "IDIE " );
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        Log.e(TAG, "DRAGGING");
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING:
//                        Log.e(TAG, "SETTLING " );
//                        break;
//                }
//
////                if (isDown && newState == RecyclerView.SCROLL_STATE_IDLE && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0){
////                    isClose = true;
////                }else{
////                    isClose = false;
////                }
//
//
//                //①拖动②第一个完全可见的item位置为0(完全到的顶端)
//                //在最高点停下来了
////                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0 ){
////                    if (isDown){
////                        //不消费RecyclerView的滚动事件
////                        mRecyclerView.setClose(true);//已经消费了这个事件!!!
////                        isDown = false;
////                        Log.e(TAG, "onScrollStateChanged: 关闭" );
////                    }else{
////                        //先走onTouchEvent再走onScroll onScrollStateChanged;如果拽不动,走onScrollStateChange;
////                        Log.e(TAG, "onScrollStateChanged: 打开" );//onTouchEvent → onScroll
////                        mRecyclerView.setClose(false);
////                    }
////                }
//            }
//
//            //判断是下拉状态
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0){
//                    isDown = false;
//                }else if(dy <= 0){
//                    isDown = true;
//                }
//                Log.e(TAG, "onScrolled: " + dy);//80 - 0 下拉为负,上拉为正
//            }
//        });
    }

    private Map<String, String> createMap() {
        //?cursor=0&count=20&iid=9281644471
        Map<String,String> map = new HashMap<>();
        map.put("cursor","0");
        map.put("count","20");
        map.put("iid","9281644471");
        return map;
    }

    @Override
    public void returnCollectionBeen(AddCollectionBeen addCollectionBeen) {
//        Logger.e("分类" + addCollectionBeen);

        Event event = new Event(EventConstants.ADD_COLLECTION_BEEN);
        event.setAddCollectionBeen(addCollectionBeen);
        EventBus.getDefault().postSticky(event);
    }

    @Override
    public void returnMusicBeen(AddMusicBeen addMusicBeen) {
//        Logger.e("音乐");
        if (addMusicBeen != null){
            mAdapter.updateResAll(addMusicBeen.getMusic_list());
        }
    }

    @Override
    public void onStartLoad() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    @Override
    public void onStopLoad() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onError(String errorInfo) {
        Logger.e("网络请求错误!" + errorInfo);
    }


//    /**
     //         * 在将事件分发到childView之前截断事件,一旦intercept返回true,onTouchEvent分发就会被调用;
     //         * 并且后续的动作直接进入onTouchEvent,直到手势的结束
     //         */
//        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                Log.e(TAG, "onInterceptTouchEvent: " );
//                return false;
//            }
//
//            //处理手势的一个触摸事件,在截断事件返回true时 -- 这个事件就消费了 -- 我们想让RecyclerView不消费
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                Log.e(TAG, "onTouchEvent: " );
//            }
//
//            //当item不想让RecyclerView或器父类截断事件时调用
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//                Log.e(TAG, "onRequestDisallowInterceptTouchEvent: " );
//            }
//        });

}
