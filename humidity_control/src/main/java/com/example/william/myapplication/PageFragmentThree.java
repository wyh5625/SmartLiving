package com.example.william.myapplication;

/**
 * Created by William on 2017/4/19.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class PageFragmentThree extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    public static String time_Input;
    public static boolean Pressed1 = false;
    public static boolean Pressed2 = false;
    public static ImageButton dryButton, wetButton;
    private TextView timeInput;
    public PageFragmentThree() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout_three, container, false);
        View mainView = inflater.inflate(R.layout.pager_tab_strip_activity, container, false);

        dryButton = (ImageButton) rootView.findViewById(R.id.dry_button);
        wetButton = (ImageButton) rootView.findViewById(R.id.wet_button);
        final Button clockButton = (Button)rootView.findViewById(R.id.clock);
        timeInput = (TextView)rootView.findViewById(R.id.time_input);

        if(timeInput != null) {
            if (time_Input.isEmpty())
                timeInput.setText("0");
            else{
                timeInput.setText(time_Input);

            }
        }


        clockButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                double figure = Double.parseDouble(timeInput.getText().toString());
                    figure += 0.5;
                    figure %= 12.5;
                    timeInput.setText(""+figure);
            }
        });

        dryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(HumidityControlMainActivity.toggleButton.isChecked() && Pressed1) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure to switch off the machine?")
                            .setCancelable(false)
                            .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Pressed1 =! Pressed1;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 100ms
                                            dryButton.setPressed(Pressed1);
                                        }
                                    }, 100);
                                    HumidityControlMainActivity.toggleButton.setChecked(false);
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Pressed1 = true;
                                    dryButton.setPressed(Pressed1);

                                }
                            });
                    builder.show();
                }else {
                    Pressed1 = !Pressed1;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 100ms
                            dryButton.setPressed(Pressed1);
                        }
                    }, 100);
                    if (Pressed2) {
                        Pressed2 = !Pressed2;
                        wetButton.setPressed(Pressed2);
                    }
                }

                /*
                if(toggleButton!=null) {
                    if (!Pressed1 && !Pressed2)
                        HumidityControlMainActivity.setToggle(false);
                    else
                        HumidityControlMainActivity.setToggle(true);

                }
                */

            }
        });
        wetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HumidityControlMainActivity.toggleButton.isChecked() && Pressed2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure o switch off the machine?")
                            .setCancelable(false)
                            .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Pressed2 =! Pressed2;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 100ms
                                            wetButton.setPressed(Pressed2);
                                        }
                                    }, 100);
                                    HumidityControlMainActivity.setToggle(false);
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Pressed2 =true;
                                    wetButton.setPressed(Pressed2);

                                }
                            });
                    builder.show();
                }else{
                    Pressed2 = !Pressed2;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 100ms
                            wetButton.setPressed(Pressed2);
                        }
                    }, 100);
                    if(Pressed1){
                        Pressed1 = ! Pressed1;
                        dryButton.setPressed(Pressed1);
                    }
                }
                /*
                if(toggleButton!=null) {
                    if (!Pressed1 && !Pressed2)
                        HumidityControlMainActivity.setToggle(false);
                    else
                        HumidityControlMainActivity.setToggle(true);
                }
                */

            }
        });






        return rootView;
    }

    @Override
    public void onStop() {
        PageFragmentTwo.timeInput = timeInput.getText().toString();
        super.onStop();
    }
}