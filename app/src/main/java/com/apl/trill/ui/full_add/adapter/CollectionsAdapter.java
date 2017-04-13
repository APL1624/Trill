package com.apl.trill.ui.full_add.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.trill.R;
import com.apl.trill.R2;
import com.apl.trill.bean.AddCollectionBeen;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class CollectionsAdapter  extends RecyclerView.Adapter<CollectionsAdapter.ViewHolder> implements View.OnClickListener{

    private List<AddCollectionBeen.Mc> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CollectionsAdapter(Context context, List<AddCollectionBeen.Mc> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateResAll(List<AddCollectionBeen.Mc> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<AddCollectionBeen.Mc> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO 导入item布局
        View itemView = inflater.inflate(R.layout.add_header_item_child, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO 绑定数据源
        Glide.with(context).load(getItem(position).getAweme_cover().getUrl_list().get(0)).into(holder.mImage);
        holder.mTitle.setText(getItem(position).getName());
    }

    public AddCollectionBeen.Mc getItem(int position) {
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
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        //TODO 注入child  view
        @BindView(R2.id.add_header_title)
        TextView mTitle;

        @BindView(R2.id.add_header_image)
        ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }
}
