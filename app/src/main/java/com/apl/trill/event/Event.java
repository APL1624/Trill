package com.apl.trill.event;

import android.support.v7.widget.RecyclerView;

import com.apl.trill.bean.AddCollectionBeen;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class Event {

    private int what;

    private AddCollectionBeen addCollectionBeen;

    private RecyclerView recyclerView;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Event(int what) {
        this.what = what;
    }

    public AddCollectionBeen getAddCollectionBeen() {
        return addCollectionBeen;
    }

    public void setAddCollectionBeen(AddCollectionBeen addCollectionBeen) {
        this.addCollectionBeen = addCollectionBeen;
    }

}
