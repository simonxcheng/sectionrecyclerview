package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by simoncheng on 16-03-03.
 */
public abstract class AbstractRecyclerSectionItem {
    private int mItemViewType = 0;

    public int getItemViewType() {
        return mItemViewType;
    }

    public void setItemViewType(int viewType) {
        mItemViewType = viewType;
    }

    public abstract void bindViewHolder(RecyclerView.ViewHolder viewHolder);
}
