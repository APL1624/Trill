package com.apl.trill.ui.full_add.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.bean.AddCollectionBeen;
import com.apl.trill.bean.AddMusicBeen;
import com.apl.trill.constants.EventConstants;
import com.apl.trill.event.Event;
import com.apl.trill.ui.full_add.decoration.CollectionsDecorate;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static final String TAG = AddRecyclerAdapter.class.getSimpleName();

    private static final int HEADER_TYPE = 0;
    private static final int MUSIC_TYPE = 1;
    private List<AddMusicBeen.MusicListBean> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    private OnItemClickListener onItemClickListener;
    private CollectionsAdapter mCollectionsAdapter;
    private GridLayoutManager mLayoutManager;
    private AddCollectionBeen mStickyAddCollectionBeen;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AddRecyclerAdapter(Context context, List<AddMusicBeen.MusicListBean> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateResAll(List<AddMusicBeen.MusicListBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<AddMusicBeen.MusicListBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO 导入item布局
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case HEADER_TYPE:
                View headerView = inflater.inflate(R.layout.add_header_item, parent, false);
                headerView.setOnClickListener(this);
                holder = new HeaderHolder(headerView);
                break;
            case MUSIC_TYPE:
                View itemView = inflater.inflate(R.layout.add_music_item, parent, false);
                itemView.setOnClickListener(this);
                holder = new ViewHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        }
        return MUSIC_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER_TYPE:
                //TODO 给GridView添加适配器
                if (holder instanceof HeaderHolder) {
                    mCollectionsAdapter = new CollectionsAdapter(context, null);
                    ((HeaderHolder) holder).mCollections.setAdapter(mCollectionsAdapter);
                    mLayoutManager = ((GridLayoutManager) ((HeaderHolder) holder).mCollections.getLayoutManager());
                    mLayoutManager.setSpanCount(4);
                    ((HeaderHolder) holder).mCollections.addItemDecoration(new CollectionsDecorate());
                    mCollectionsAdapter.updateResAll(mStickyAddCollectionBeen.getMc_list());
                }
                break;
            case MUSIC_TYPE:
                if (holder instanceof ViewHolder) {
                    AddMusicBeen.MusicListBean item = getItem(position);
                    Glide.with(context).load(item.getCover_thumb().getUrl_list().get(0)).placeholder(R.mipmap.qr).into(((ViewHolder) holder).mImage);
                    ((ViewHolder) holder).mTitle.setText(item.getTitle());
                    ((ViewHolder) holder).mAuthor.setText(item.getAuthor());
                    ((ViewHolder) holder).mDuration.setText("00:" + item.getDuration());
                }

                break;
        }

    }

    @Subscribe(sticky = true)
    public void onEvent(Event event) {
        switch (event.getWhat()) {
            case EventConstants.ADD_COLLECTION_BEEN:
//                Logger.e("collection_been" + event.getAddCollectionBeen().getMc_list().size());
                mStickyAddCollectionBeen = event.getAddCollectionBeen();
                break;
        }
    }


    public AddMusicBeen.MusicListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            onItemClickListener.onItemClick(mRecyclerView, v, position, position);
        }

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.add_music_item_image)
        ImageView mImage;

        @BindView(R2.id.add_music_item_title)
        TextView mTitle;

        @BindView(R2.id.add_music_item_author)
        TextView mAuthor;

        @BindView(R2.id.add_music_item_duration)
        TextView mDuration;

        @BindView(R2.id.add_music_item_play)
        ImageView mPlay;

        @BindView(R2.id.add_music_item_loading)
        ImageView mLoading;

        @BindView(R2.id.add_music_item_pause)
        ImageView mPause;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.add_collections)
        RecyclerView mCollections;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }

}
