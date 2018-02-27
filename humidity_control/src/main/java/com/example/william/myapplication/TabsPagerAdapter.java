package com.example.william.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final String ARG_PAGE_NUMBER = "page_number";
    Fragment[] fragments = new Fragment[3];
    public static int tabPosition=1;


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        PageFragmentOne fgOne = new PageFragmentOne();
        PageFragmentTwo fgTwo = new PageFragmentTwo();
        PageFragmentThree fgThree = new PageFragmentThree();

        Bundle args1 = new Bundle();
        Bundle args2 = new Bundle();
        Bundle args3 = new Bundle();
        args1.putInt(ARG_PAGE_NUMBER, 1);
        args2.putInt(ARG_PAGE_NUMBER, 2);
        args3.putInt(ARG_PAGE_NUMBER, 3);

        fgOne.setArguments(args1);
        fgTwo.setArguments(args2);
        fgThree.setArguments(args3);

        fragments[0]=fgOne;
        fragments[1]=fgTwo;
        fragments[2]=fgThree;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    public static int getTabPosition() {
        return tabPosition;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Auto";
            case 1:
                return "Manual";
            case 2:
                return "Timer";
            default:
                return "none";
        }

    }


}