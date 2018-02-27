package com.example.william.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by William on 2017/4/21.
 */

class Pop extends Activity implements SeekBar.OnSeekBarChangeListener {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    final String SD = "sdStatus";
    final String N = "nStatus";
    final String SW = "swStatus";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTheme(R.style.DialogTheme);


        setContentView(R.layout.popwindow);

        read(R.id.sd_status,SD);
        read(R.id.n_status,N);
        read(R.id.sw_status,SW);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        SeekBar seekBar1,seekBar2,seekBar3;
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        if(!((TextView)findViewById(R.id.sd_status)).getText().toString().isEmpty())
            seekBar1.setProgress(Integer.parseInt(((TextView)findViewById(R.id.sd_status)).getText().toString()));
        if(!((TextView)findViewById(R.id.n_status)).getText().toString().isEmpty())
            seekBar2.setProgress(Integer.parseInt(((TextView)findViewById(R.id.n_status)).getText().toString()));
        if(!((TextView)findViewById(R.id.sw_status)).getText().toString().isEmpty())
            seekBar3.setProgress(Integer.parseInt(((TextView)findViewById(R.id.sw_status)).getText().toString()));


        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        TextView textView1,textView2,textView3;
        textView1 = (TextView)findViewById(R.id.sd_status);
        textView2 = (TextView)findViewById(R.id.n_status);
        textView3 = (TextView)findViewById(R.id.sw_status);

        if(seekBar.getId()==R.id.seekBar1)
        {
            textView1.setText(""+progress);
        }
        else if(seekBar.getId()==R.id.seekBar2)
        {
            textView2.setText(""+progress);
        }
        else if(seekBar.getId()==R.id.seekBar3)
        {
            textView3.setText("" + progress);
        }
        /*
            case R.id.seekBar1:
                textView1.setText(""+progress); break;
            case R.id.seekBar2:
                textView2.setText(""+progress); break;
            case R.id.seekBar3:
                textView3.setText(""+progress); break;
            default:
                break;
        }
        */

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int value = seekBar.getProgress();
    }

    @Override
    protected void onStop() {
        save(R.id.sd_status,SD);
        save(R.id.n_status,N);
        save(R.id.sw_status,SW);
        super.onStop();
    }

    public void save(int id, String name){
        TextView statusSD = (TextView)findViewById(id);
        String SDvalue = (String)statusSD.getText();
        String SDkey = name;
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SDkey, SDvalue);
        editor.commit();

    }
    public void read(int id, String name){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String status = sharedPreferences.getString(name,"");
        TextView statusSD = (TextView)findViewById(id);
        statusSD.setText(status);
    }
}
