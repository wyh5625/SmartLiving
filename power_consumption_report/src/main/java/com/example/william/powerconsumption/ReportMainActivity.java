package com.example.william.powerconsumption;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ReportMainActivity extends Activity {
    public static String humidity_time = "0";
    public static String light_time = "0";
    public static double power_humidity;
    public static double power_light;
    public static double power_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        double humidity_rate=Math.random()*(550-200)+200;
        double light_rate = Math.random()*(350-250)+250.0;
        DecimalFormat df = new DecimalFormat("#.##");
        humidity_time = df.format(Math.round(Math.random() * 12))+"";
        light_time = df.format(Math.round(Math.random()*24)) + "";



    /*
        File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS);

        File file = new File(sdcard,"humidity_time");
        String read="";
        try {
            FileInputStream fi = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fi);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer sb = new StringBuffer();

            while ((read = bufferedReader.readLine())!=null) {
                sb.append(read);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if(read.isEmpty())
            humidity_time = "0";
        else
            humidity_time = read;
            */


        power_humidity = Double.parseDouble(df.format(humidity_rate*Double.parseDouble(humidity_time)/1000)) ;


        power_light = Double.parseDouble(df.format(light_rate*Double.parseDouble(light_time)/1000));
        power_total = power_humidity + power_light;

        TextView tot_p = (TextView)findViewById(R.id.total_power);
        tot_p.setText("Total:\n" + df.format(power_total) +"kWh");

        ProgressBar pg = (ProgressBar)findViewById(R.id.progressBar);
        pg.setProgress(100);

        String[] apps = {"Humidifier", "Lighting"};
        ListAdapter listAdapter = new CustomAdapter(this, apps);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String food = String.valueOf(parent.getItemAtPosition(position));
                //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
            }
        });

    }


}
