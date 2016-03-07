package com.example.android.recyclerview;

import java.util.ArrayList;

/**
 * Created by simoncheng on 16-03-03.
 */
public class SectionItemPositionUnit {

    /**
     * -1 if it is the section header
     */
    private int mPositionInSection;

    private int mSectionIndex;

    //TODO: do we need it?
    private int mSectionHeaderPositionInFlatList;

    public int getPositionInSection() {
        return mPositionInSection;
    }

    public int getSectionIndex() {
        return mSectionIndex;
    }

    public int getSectionHeaderPositionInFlatList() {
        return mSectionHeaderPositionInFlatList;
    }

    public SectionItemPositionUnit(int sectionIndex, int positionInSection, int sectionHeaderPositionInFlatList) {

        if (sectionIndex < 0 || positionInSection < -1 || sectionHeaderPositionInFlatList < 0) {
            throw new IllegalArgumentException("sectionIndex =" + sectionIndex
                    + " positionInSection=" + positionInSection +
            " sectionHeaderPositionInFlatList=" + sectionHeaderPositionInFlatList);
        }
        mSectionIndex = sectionIndex;
        mPositionInSection = positionInSection;
        mSectionHeaderPositionInFlatList = sectionHeaderPositionInFlatList;
    }

    public static SectionItemPositionUnit[] buildPositionArray(ArrayList<AbstractRecyclerSection> sections) {

        SectionItemPositionUnit[] sectionItemPositionArray;

        if (sections == null) {
            //TODO crash in debug
            sectionItemPositionArray = new SectionItemPositionUnit[0];
            return sectionItemPositionArray;
        }

        {
            int count = 0;
            for (AbstractRecyclerSection abstractRecyclerSection : sections) {
                count++;
                count += abstractRecyclerSection.getVisibleItemCount();
            }

            sectionItemPositionArray = new SectionItemPositionUnit[count];
        }

        int flatIndex = -1;

        for (int sectionIndex = 0; sectionIndex < sections.size(); sectionIndex++) {
            flatIndex++;
            final int itemCount = sections.get(sectionIndex).getVisibleItemCount();

            final int headerPositionInFlatList = flatIndex;

            {
                SectionItemPositionUnit sectionHeaderPositionUnit =
                        new SectionItemPositionUnit(sectionIndex, -1, headerPositionInFlatList);

                sectionItemPositionArray[flatIndex] = sectionHeaderPositionUnit;
            }

            for (int positionInSection = 0; positionInSection < itemCount; positionInSection++) {
                flatIndex++;
                SectionItemPositionUnit itemPositionUnit = new SectionItemPositionUnit(sectionIndex,
                        positionInSection, headerPositionInFlatList);
                sectionItemPositionArray[flatIndex] = itemPositionUnit;
            }
        }

        return sectionItemPositionArray;
    }
}
