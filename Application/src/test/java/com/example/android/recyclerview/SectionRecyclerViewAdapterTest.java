package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by simoncheng on 16-03-04.
 */
public class SectionRecyclerViewAdapterTest {

    @Test
    public void testGetSectionStartPosition() throws Exception {

        ArrayList<AbstractRecyclerSection> sections = new ArrayList<>();
        sections.add(helper_buildSection(0));
        sections.add(helper_buildSection(2));
        sections.add(helper_buildSection(0));
        sections.add(helper_buildSection(3));
        sections.add(helper_buildSection(0));

        assertEquals(SectionRecyclerViewAdapter.getSectionStartPosition(sections, 0), 0);
        assertEquals(SectionRecyclerViewAdapter.getSectionStartPosition(sections, 1), 1);
        assertEquals(SectionRecyclerViewAdapter.getSectionStartPosition(sections, 2), 4);
        assertEquals(SectionRecyclerViewAdapter.getSectionStartPosition(sections, 3), 5);
        assertEquals(SectionRecyclerViewAdapter.getSectionStartPosition(sections, 4), 9);
    }

    private AbstractRecyclerSection helper_buildSection(int sectionSize) {
        ArrayList<AbstractRecyclerSectionItem> items = new ArrayList<>();
        for (int i = 0; i < sectionSize; i++) {
            items.add(new AbstractRecyclerSectionItemMock());
        }
        AbstractRecyclerSection abstractRecyclerSection = new AbstractRecyclerSectionMock(items);
        return abstractRecyclerSection;
    }

    static class AbstractRecyclerSectionMock extends AbstractRecyclerSection {
        public AbstractRecyclerSectionMock(ArrayList<AbstractRecyclerSectionItem> items) {
            super(items);
        }
        public void bindSectionHeaderViewHolder(RecyclerView.ViewHolder sectionViewHolder) {};
    }

    static class AbstractRecyclerSectionItemMock extends AbstractRecyclerSectionItem {
        public void bindViewHolder(RecyclerView.ViewHolder viewHolder){};
    }
}