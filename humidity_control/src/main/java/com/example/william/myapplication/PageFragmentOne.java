package com.example.william.myapplication;

/**
 * Created by William on 2017/4/19.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;


public class PageFragmentOne extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    public static int rg_id;




    public PageFragmentOne() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout_one, container, false);

        if(rg_id != 0) {
            RadioButton radioBtn = (RadioButton) rootView.findViewById(rg_id);
            if(radioBtn!=null)
                radioBtn.setChecked(true);
        }

        ImageButton setting = (ImageButton) rootView.findViewById(R.id.imageButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pop.class));
            }

        });


        return rootView;
    }
}