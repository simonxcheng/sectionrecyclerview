package com.example.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by simoncheng on 16-03-03.
 */
public class SectionRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private static final int SECTION_TYPE = 0;

    private ArrayList<AbstractRecyclerSection> mSections = null;
    private SectionItemPositionUnit[] mSectionItemPositionArray;

    private RecyclerViewHolderBuilder mRecyclerViewHolderBuilder;

    public SectionRecyclerViewAdapter(Context context, RecyclerViewHolderBuilder viewHolderBuilder) {
        mContext = context;
        mRecyclerViewHolderBuilder = viewHolderBuilder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
        return mRecyclerViewHolderBuilder.createViewHolder(parent, typeView);
    }

    public void expandSection(int sectionIndex) {
        AbstractRecyclerSection section = mSections.get(sectionIndex);

        if (section.getIsExpanded()) {
            return;
        }

        section.setIsExpanded(true);
        final int sectionStartPosition = getSectionStartPosition(mSections, sectionIndex);

        mSectionItemPositionArray = SectionItemPositionUnit.buildPositionArray(mSections);
        notifyItemChanged(sectionStartPosition);

        if (section.getItemCount() > 0) {
            notifyItemRangeInserted(sectionStartPosition + 1, section.getItemCount());
        }
    }

    public void collapseSection(int sectionIndex) {
        AbstractRecyclerSection section = mSections.get(sectionIndex);

        if (!section.getIsExpanded()) {
            return;
        }

        section.setIsExpanded(false);
        final int sectionStartPosition = getSectionStartPosition(mSections, sectionIndex);
        mSectionItemPositionArray = SectionItemPositionUnit.buildPositionArray(mSections);

        notifyItemChanged(sectionStartPosition);

        if (section.getItemCount() > 0) {
            notifyItemRangeRemoved(sectionStartPosition + 1, section.getItemCount());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SectionItemPositionUnit positionUnit = mSectionItemPositionArray[position];
        if (positionUnit.getPositionInSection() == AbstractRecyclerSection.SECTION_HEADER_POSITION) {
            mSections.get(positionUnit.getSectionIndex()).bindSectionHeaderViewHolder(viewHolder, positionUnit.getSectionIndex());
        } else {
            mSections.get(positionUnit.getSectionIndex()).getItem(positionUnit.getPositionInSection()).bindViewHolder(viewHolder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        SectionItemPositionUnit positionUnit = mSectionItemPositionArray[position];
        return mSections.get(positionUnit.getSectionIndex()).getItemViewType(positionUnit.getPositionInSection());
    }

    public void setSections(ArrayList<AbstractRecyclerSection> sections) {
        mSections = sections;
        mSectionItemPositionArray = SectionItemPositionUnit.buildPositionArray(mSections);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSectionItemPositionArray.length;
    }

    public static int getSectionStartPosition(ArrayList<AbstractRecyclerSection> sections, int index) {
        if (index == 0) return 0;

        int pos = -1;
        for (int i = 0; i < index; i++) {
            pos++; //For header
            pos += sections.get(i).getVisibleItemCount();
        }

        return pos + 1;
    }
}
