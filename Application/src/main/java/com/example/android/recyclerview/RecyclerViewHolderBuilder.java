package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by simoncheng on 16-03-03.
 */
public interface RecyclerViewHolderBuilder {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int typeView);
}
