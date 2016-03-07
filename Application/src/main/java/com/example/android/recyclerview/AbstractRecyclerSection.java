package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by simoncheng on 16-03-03.
 */
public abstract class AbstractRecyclerSection {

    public static final int SECTION_HEADER_POSITION = -1;

    private ArrayList<AbstractRecyclerSectionItem> mItems = null;

    private int mHeaderViewType = 0;

    private boolean mIsExpanded = true;


    public int getItemViewType(int positionInSection) {
        if (positionInSection == SECTION_HEADER_POSITION) {
            return mHeaderViewType;
        } else {
            return mItems.get(positionInSection).getItemViewType();
        }
    }

    public void setIsExpanded(boolean expanded) {
        mIsExpanded = expanded;
    }

    public boolean getIsExpanded() {
        return mIsExpanded;
    }

    abstract public void bindSectionHeaderViewHolder(RecyclerView.ViewHolder sectionViewHolder, int sectionIndex);


//    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, int positionInSection) {
//        if (positionInSection == SECTION_HEADER_POSITION) {
//            bindSectionHeaderViewHolder(viewHolder);
//        } else {
//            mItems.get(positionInSection).bindViewHolder(viewHolder);
//        }
//    }

    public void setHeaderViewType(int sectionHeaderViewType) {
        mHeaderViewType = sectionHeaderViewType;
    }

    public AbstractRecyclerSection(ArrayList<AbstractRecyclerSectionItem> items) {
        mItems = items;
    }

    public int getVisibleItemCount() {
        if (mIsExpanded) {
            return mItems.size();
        } else {
            return 0;
        }
    }

    public int getItemCount() {
        return mItems.size();
    }

    public AbstractRecyclerSectionItem getItem(int itemIndex) {
        return mItems.get(itemIndex);
    }
}
