package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by simoncheng on 16-03-03.
 */
public class SectionItemPositionUnitTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAllSectionHasItems() throws Exception {
        ArrayList<AbstractRecyclerSection> sections = new ArrayList<>();
        sections.add(helper_buildSection(2));
        sections.add(helper_buildSection(3));
        sections.add(helper_buildSection(1));

        SectionItemPositionUnit[] array = SectionItemPositionUnit.buildPositionArray(sections);
        assertEquals(array.length, 9);

        //Section 0
        assertEquals(array[0].getSectionIndex(), 0);
        assertEquals(array[0].getPositionInSection(), -1);
        assertEquals(array[0].getSectionHeaderPositionInFlatList(), 0);

        assertEquals(array[1].getSectionIndex(), 0);
        assertEquals(array[1].getPositionInSection(), 0);
        assertEquals(array[1].getSectionHeaderPositionInFlatList(), 0);

        assertEquals(array[2].getSectionIndex(), 0);
        assertEquals(array[2].getPositionInSection(), 1);
        assertEquals(array[2].getSectionHeaderPositionInFlatList(), 0);

        //Section 1
        assertEquals(array[3].getSectionIndex(), 1);
        assertEquals(array[3].getPositionInSection(), -1);
        assertEquals(array[3].getSectionHeaderPositionInFlatList(), 3);

        assertEquals(array[4].getSectionIndex(), 1);
        assertEquals(array[4].getPositionInSection(), 0);
        assertEquals(array[4].getSectionHeaderPositionInFlatList(), 3);

        assertEquals(array[6].getSectionIndex(), 1);
        assertEquals(array[6].getPositionInSection(), 2);
        assertEquals(array[6].getSectionHeaderPositionInFlatList(), 3);

        //Section 2
        assertEquals(array[7].getSectionIndex(), 2);
        assertEquals(array[7].getPositionInSection(), -1);
        assertEquals(array[7].getSectionHeaderPositionInFlatList(), 7);

        assertEquals(array[8].getSectionIndex(), 2);
        assertEquals(array[8].getPositionInSection(), 0);
        assertEquals(array[8].getSectionHeaderPositionInFlatList(), 7);
    }

    @Test
    public void testSomeSectionHasNoItems() throws Exception {
        ArrayList<AbstractRecyclerSection> sections = new ArrayList<>();
        sections.add(helper_buildSection(0));
        sections.add(helper_buildSection(2));
        sections.add(helper_buildSection(0));
        sections.add(helper_buildSection(3));
        sections.add(helper_buildSection(0));

        SectionItemPositionUnit[] array = SectionItemPositionUnit.buildPositionArray(sections);
        assertEquals(array.length, 10);

        //Section 0
        assertEquals(array[0].getSectionIndex(), 0);
        assertEquals(array[0].getPositionInSection(), -1);
        assertEquals(array[0].getSectionHeaderPositionInFlatList(), 0);

        //Section 1
        assertEquals(array[1].getSectionIndex(), 1);
        assertEquals(array[1].getPositionInSection(), -1);
        assertEquals(array[1].getSectionHeaderPositionInFlatList(), 1);


        assertEquals(array[2].getSectionIndex(), 1);
        assertEquals(array[2].getPositionInSection(), 0);
        assertEquals(array[2].getSectionHeaderPositionInFlatList(), 1);

        //Section 2
        assertEquals(array[4].getSectionIndex(), 2);
        assertEquals(array[4].getPositionInSection(), -1);
        assertEquals(array[4].getSectionHeaderPositionInFlatList(), 4);

        //Section 3
        assertEquals(array[5].getSectionIndex(), 3);
        assertEquals(array[5].getPositionInSection(), -1);
        assertEquals(array[5].getSectionHeaderPositionInFlatList(), 5);

        assertEquals(array[8].getSectionIndex(), 3);
        assertEquals(array[8].getPositionInSection(), 2);
        assertEquals(array[8].getSectionHeaderPositionInFlatList(), 5);

        //Section 4
        assertEquals(array[9].getSectionIndex(), 4);
        assertEquals(array[9].getPositionInSection(), -1);
        assertEquals(array[9].getSectionHeaderPositionInFlatList(), 9);
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