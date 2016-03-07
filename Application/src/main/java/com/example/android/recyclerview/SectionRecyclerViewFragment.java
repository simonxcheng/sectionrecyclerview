package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simoncheng on 16-03-03.
 */
public class SectionRecyclerViewFragment extends Fragment{
    RecyclerView mRecyclerView;
    SimpleAdapter mAdapter;

    public static String[] sCheeseStrings;

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
        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(getActivity(), sCheeseStrings);


        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(5,"Section 2"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(12,"Section 3"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(14,"Section 4"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(20,"Section 5"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getActivity(),
                R.layout.section,R.id.section_text, mAdapter);

        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);
    }
}
