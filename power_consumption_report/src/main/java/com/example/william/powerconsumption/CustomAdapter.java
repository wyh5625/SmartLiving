package com.example.william.powerconsumption;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by William on 2017/4/22.
 */

public class CustomAdapter extends ArrayAdapter<String>{
    public CustomAdapter( Context context,  String[] foods) {
        super(context, R.layout.custom_row,foods);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater listInflater = LayoutInflater.from(getContext());
        View customView = listInflater.inflate(R.layout.custom_row, parent, false);
        String singleFoodItem = getItem(position);
        TextView listText =(TextView) customView.findViewById(R.id.textView);
        ImageView image = (ImageView)customView.findViewById((R.id.guitar));
        TextView detailText = (TextView) customView.findViewById(R.id.detail_text);

        listText.setText("Time: \n\n"+"Power: ");
        if(position == 0) {
            detailText.setText(ReportMainActivity.humidity_time+" hr \n\n"+ ReportMainActivity.power_humidity+" kWh ");
            image.setImageResource(R.mipmap.humidity);
        }
        else if(position == 1) {
            detailText.setText(ReportMainActivity.light_time+" hr \n\n"+ ReportMainActivity.power_light+" kWh ");
            image.setImageResource(R.mipmap.humidity);
        }


        return customView;
    }
}
