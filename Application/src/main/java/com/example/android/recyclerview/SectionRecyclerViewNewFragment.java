package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by simoncheng on 16-03-04.
 */
public class SectionRecyclerViewNewFragment extends Fragment {
    RecyclerView mRecyclerView;
    SectionRecyclerViewAdapter mAdapter;

    public static String[] sCheeseStrings;

    public static int CHEESE_SECTION_HEADER_VIEW_TYPE = 0;
    public static int CHEESE_ITEM_VIEW_TYPE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.section_recycler_view_frag, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Your RecyclerView
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));

        final int cheeseCount = 30;
        sCheeseStrings = new String[cheeseCount];
        for (int i = 0; i < cheeseCount; i++) {
            sCheeseStrings[i] = "Cheese number " + i;
        }


        //This is the code to provide a sectioned list
        ArrayList<AbstractRecyclerSection> sections =
                new ArrayList<>();

        //Sections
        sections.add(buildSection(0, 3, CHEESE_SECTION_HEADER_VIEW_TYPE, CHEESE_ITEM_VIEW_TYPE));
        sections.add(buildSection(1, 5, CHEESE_SECTION_HEADER_VIEW_TYPE, CHEESE_ITEM_VIEW_TYPE));
        sections.add(buildSection(2, 0, CHEESE_SECTION_HEADER_VIEW_TYPE, CHEESE_ITEM_VIEW_TYPE));
        sections.add(buildSection(3, 0, CHEESE_SECTION_HEADER_VIEW_TYPE, CHEESE_ITEM_VIEW_TYPE));
        sections.add(buildSection(4, 8, CHEESE_SECTION_HEADER_VIEW_TYPE, CHEESE_ITEM_VIEW_TYPE));

        //Your RecyclerView.Adapter
        mAdapter = new SectionRecyclerViewAdapter(getActivity(), new ViewHolderGenerator());

//        //Add your adapter to the sectionAdapter
//        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
//        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
//                SimpleSectionedRecyclerViewAdapter(getActivity(),
//                R.layout.section,R.id.section_text, mAdapter);
//
//        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mAdapter.setSections(sections);
        mRecyclerView.setAdapter(mAdapter);
    }

    static class ViewHolderGenerator implements RecyclerViewHolderBuilder {
        public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int typeView) {
            if (typeView == CHEESE_SECTION_HEADER_VIEW_TYPE) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section, parent, false);
                return new SimpleSectionedRecyclerViewAdapter.SectionViewHolder(view, R.id.section_text);

            }else if (typeView == CHEESE_ITEM_VIEW_TYPE){
                final View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new SimpleAdapter.SimpleViewHolder(view);
            }
            return null;
        }
    }

    CheeseSection buildSection(int sectionIndex, int size, int headerViewType, int itemViewType) {
        ArrayList<AbstractRecyclerSectionItem> items = new ArrayList<>();

        for (int i = 0 ; i < size; i++) {
            CheeseSectionItem cheeseSectionItem = new CheeseSectionItem();
            cheeseSectionItem.setItemViewType(itemViewType);
            cheeseSectionItem.mCheeseName = "Cheese " + sectionIndex + ":" + i;
            items.add(cheeseSectionItem);
        }

        CheeseSection section = new CheeseSection(items);
        section.setHeaderViewType(headerViewType);
        section.mSectionTitle = "Section " + sectionIndex;
        return section;
    }

    class CheeseSection extends AbstractRecyclerSection {

        public String mSectionTitle;

        public CheeseSection(ArrayList<AbstractRecyclerSectionItem> items) {
            super(items);
        }

        @Override
        public void bindSectionHeaderViewHolder(RecyclerView.ViewHolder sectionViewHolder, int sectionIndex) {
            if (sectionViewHolder instanceof SimpleSectionedRecyclerViewAdapter.SectionViewHolder) {
                ((SimpleSectionedRecyclerViewAdapter.SectionViewHolder) sectionViewHolder).title.setText(mSectionTitle);

                final Switch toogle =  ((SimpleSectionedRecyclerViewAdapter.SectionViewHolder) sectionViewHolder).toogle;

                if (getItemCount() == 0) {
                    toogle.setVisibility(View.GONE);
                    return;
                } else {
                    toogle.setVisibility(View.VISIBLE);
                }
                toogle.setChecked(getIsExpanded());
                toogle.setTag(new Integer(sectionIndex));
                toogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mAdapter.expandSection((Integer)toogle.getTag());
                        } else {
                            mAdapter.collapseSection((Integer)toogle.getTag());
                        }
                    }
                });
            }
        }
    }

    static class CheeseSectionItem extends AbstractRecyclerSectionItem {

        public String mCheeseName;

        @Override
        public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof SimpleAdapter.SimpleViewHolder) {
                ((SimpleAdapter.SimpleViewHolder)viewHolder).title.setText(mCheeseName);
            }
        }
    }
}
