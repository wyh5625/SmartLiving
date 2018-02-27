package com.example.william.myapplication;

/**
 * Created by William on 2017/4/19.
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PageFragmentTwo extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    public static String tunerInput;
    public static String timeInput;

    public PageFragmentTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout_two, container, false);
        final TextView tunerText = (TextView) rootView.findViewById(R.id.tuner_input);

        if(tunerText != null) {
            if (tunerInput.isEmpty())
                tunerInput = "0";
            tunerText.setText(tunerInput);
        }



        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(100); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(1); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        final Button addButton = (Button)rootView.findViewById(R.id.add_button);
        final Button minorButton = (Button)rootView.findViewById(R.id.minor_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                int figure = Integer.parseInt(tunerText.getText().toString());
                if(figure < 100){
                    figure ++;
                    tunerText.setText(""+figure);
                }
                addButton.startAnimation(animation);
            }
        });

        minorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                int figure = Integer.parseInt(tunerText.getText().toString());
                if(figure > 0){
                    figure --;
                    tunerText.setText(""+figure);
                }
                minorButton.startAnimation(animation);
            }
        });

        return rootView;
    }



}