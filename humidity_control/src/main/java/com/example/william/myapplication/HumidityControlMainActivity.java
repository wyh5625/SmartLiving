package com.example.william.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class HumidityControlMainActivity extends FragmentActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public String toggle_status = "toggleS";
    final String timeInput = "time_input";
    final String tunerInput = "tuner_input";
    final String tabStatus= "tab_status";
    final String rg = "rg";
    private ViewPager pager;
    public static int oldPos = 0;
    static Switch toggleButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_tab_strip_activity);


        final TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TextView huText = (TextView) findViewById(R.id.text_humi);
        TextView stText = (TextView) findViewById(R.id.text_store);
        huText.setText((int)(Math.random()*(100-70)+70)+"%");
        stText.setText((int)(Math.random()*100)+"%");



        toggleButton = (Switch) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(HumidityControlMainActivity.this, "enter", Toast.LENGTH_LONG).show();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("power",MODE_PRIVATE);
        pager.setCurrentItem(sharedPreferences.getInt(tabStatus,0));


        PageFragmentTwo.tunerInput = sharedPreferences.getString(tunerInput,"0");
        PageFragmentThree.time_Input = sharedPreferences.getString(timeInput,"0");

        PageFragmentOne.rg_id = sharedPreferences.getInt(rg, 0);

        toggleButton.setChecked(sharedPreferences.getBoolean(toggle_status, false));



        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    if(pager.getCurrentItem() == 2 && !PageFragmentThree.Pressed1 && !PageFragmentThree.Pressed2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(HumidityControlMainActivity.this);
                    builder.setMessage("You have not chosen humidify/dehumidify button!!!")
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    toggleButton.setChecked(false);

                                }
                            })
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    toggleButton.setChecked(false);

                                }
                            });
                    builder.show();
                }

            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(toggleButton.isChecked())
                    toggleButton.setChecked(false);
                if(PageFragmentThree.Pressed2 || PageFragmentThree.Pressed1){
                    PageFragmentThree.Pressed1 = false;
                    PageFragmentThree.Pressed2 = false;
                    PageFragmentThree.dryButton.setPressed(false);
                    PageFragmentThree.wetButton.setPressed(false);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if(toggleButton.isChecked() && oldPos != position){

                    AlertDialog.Builder builder = new AlertDialog.Builder(HumidityControlMainActivity.this);
                    builder.setMessage(R.string.dialog_fire_missiles)
                            .setCancelable(false)
                            .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    toggleButton.setChecked(false);
                                    oldPos = position;
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    pager.setCurrentItem(oldPos);
                                }
                            });
                    builder.show();
                }else
                    oldPos =position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */




    }

    @Override
    protected void onStop() {
        //LayoutInflater inflater=this.getLayoutInflater();
        //View view1=inflater.inflate(R.layout.fragment_page_layout_one, null);


        SharedPreferences sharedPreference = getSharedPreferences("power",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();

        editor.putInt(tabStatus,pager.getCurrentItem());
        editor.putBoolean(toggle_status,false);

        TextView tunerText = (TextView) findViewById(R.id.tuner_input);
        TextView timeText = (TextView) findViewById(R.id.time_input);


        RadioGroup radioGp= (RadioGroup)findViewById(R.id.radio_group);

        if(timeText!=null)
            editor.putString(timeInput, timeText.getText().toString());
        if(tunerText != null)
            editor.putString(tunerInput, tunerText.getText().toString());
        if(radioGp != null)
            editor.putInt(rg,radioGp.getCheckedRadioButtonId());

        editor.commit();


        /*
        String content = (Math.random()*(12-0))+"";
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS), "humidity_time");
            file.mkdir();
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            Log.e("try","fail");
            e.printStackTrace();
        }
        */

        super.onStop();
    }

    public static void setToggle(boolean t){
        toggleButton.setChecked(t);
    }



}