package com.example.smartliving;

import java.util.*;
import com.triggertrap.seekarc.SeekArc;

import android.app.*;
import android.content.*;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.*;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.*;
import android.view.*;
import android.widget.*;

public class LightingManagerActivity extends Activity 
{
	private ArrayList<Integer> lightAuto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lighting_manager);
		
		//set light auto value, technically it should determine the value according to the environment
		lightAuto=new ArrayList<Integer>();
		lightAuto.add(20);
		lightAuto.add(40);
		lightAuto.add(60);
		lightAuto.add(80);
		lightAuto.add(100);
		
		DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();    
		float h = displayMetrics.heightPixels;
		float w = displayMetrics.widthPixels;
		
		BitmapFactory.Options dimensions = new BitmapFactory.Options(); 
		dimensions.inJustDecodeBounds = true;
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_button_icon, dimensions);
		
		final SharedPreferences lightPref = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor lightEditor = lightPref.edit();
		
		//Debug
		//lightEditor.clear();
		//lightEditor.apply();
		
		final ProgressBar light1=(ProgressBar) findViewById(R.id.light1);
		light1.setX(w*0.275f);
		light1.setY(h*0.15f);
		light1.getLayoutParams().height=dimensions.outHeight;
		light1.requestLayout();
		
		if(lightPref.getBoolean("light1On", false)==false)
		{
			light1.setProgress(0);
		}
		else
		{
			light1.setProgress(lightPref.getInt("light1Intensity", 0));
		}
		
		light1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(lightPref.getBoolean("light1On", false)==false)
				{
					lightEditor.putBoolean("light1On", true);
					lightEditor.apply();
					light1.setProgress(lightPref.getInt("light1Intensity", 0));
				}
				else
				{
					lightEditor.putBoolean("light1On", false);
					lightEditor.putInt("light1Intensity", light1.getProgress());
					lightEditor.apply();
					light1.setProgress(0);
				}
			}
			
		});
		
		light1.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) 
			{
				lightingPopUp(light1).show();			
				return false;
			}
		});
		
		final ProgressBar light2=(ProgressBar) findViewById(R.id.light2);
		light2.setX(w*0.55f);
		light2.setY(h*0.20f);
		light2.getLayoutParams().height=dimensions.outHeight;
		light2.requestLayout();

		if(lightPref.getBoolean("light2On", false)==false)
		{
			light2.setProgress(0);
		}
		else
		{
			light2.setProgress(lightPref.getInt("light2Intensity", 0));
		}
		
		light2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(lightPref.getBoolean("light2On", false)==false)
				{
					lightEditor.putBoolean("light2On", true);
					lightEditor.apply();
					light2.setProgress(lightPref.getInt("light2Intensity", 0));
				}
				else
				{
					lightEditor.putBoolean("light2On", false);
					lightEditor.putInt("light2Intensity", light2.getProgress());
					lightEditor.apply();
					light2.setProgress(0);
				}
			}
			
		});
		
		light2.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) 
			{
				lightingPopUp(light2).show();			
				return false;
			}
		});
		
		
		final ProgressBar light3=(ProgressBar) findViewById(R.id.light3);
		light3.setX(w*0.24f);
		light3.setY(h*0.55f);
		light3.getLayoutParams().height=dimensions.outHeight;
		light3.requestLayout();

		if(lightPref.getBoolean("light3On", false)==false)
		{
			light3.setProgress(0);
		}
		else
		{
			light3.setProgress(lightPref.getInt("light3Intensity", 0));
		}
		
		light3.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(lightPref.getBoolean("light3On", false)==false)
				{
					lightEditor.putBoolean("light3On", true);
					lightEditor.apply();
					light3.setProgress(lightPref.getInt("light3Intensity", 0));
				}
				else
				{
					lightEditor.putBoolean("light3On", false);
					lightEditor.putInt("light3Intensity", light3.getProgress());
					lightEditor.apply();
					light3.setProgress(0);
				}
			}
			
		});
		
		light3.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) 
			{
				lightingPopUp(light3).show();			
				return false;
			}
		});
		
		final ProgressBar light4=(ProgressBar) findViewById(R.id.light4);
		light4.setX(w*0.37f);
		light4.setY(h*0.60f);
		light4.getLayoutParams().height=dimensions.outHeight;
		light4.requestLayout();

		if(lightPref.getBoolean("light4On", false)==false)
		{
			light4.setProgress(0);
		}
		else
		{
			light4.setProgress(lightPref.getInt("light4Intensity", 0));
		}
		
		light4.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(lightPref.getBoolean("light4On", false)==false)
				{
					lightEditor.putBoolean("light4On", true);
					lightEditor.apply();
					light4.setProgress(lightPref.getInt("light4Intensity", 0));
				}
				else
				{
					lightEditor.putBoolean("light4On", false);
					lightEditor.putInt("light4Intensity", light4.getProgress());
					lightEditor.apply();
					light4.setProgress(0);
				}
			}
			
		});
		
		light4.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) 
			{
				lightingPopUp(light4).show();			
				return false;
			}
		});
		
		final ProgressBar light5=(ProgressBar) findViewById(R.id.light5);
		light5.setX(w*0.58f);
		light5.setY(h*0.60f);
		light5.getLayoutParams().height=dimensions.outHeight;
		light5.requestLayout();

		if(lightPref.getBoolean("light5On", false)==false)
		{
			light5.setProgress(0);
		}
		else
		{
			light5.setProgress(lightPref.getInt("light5Intensity", 0));
		}
		
		light5.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(lightPref.getBoolean("light5On", false)==false)
				{
					lightEditor.putBoolean("light5On", true);
					lightEditor.apply();
					light5.setProgress(lightPref.getInt("light5Intensity", 0));
				}
				else
				{
					lightEditor.putBoolean("light5On", false);
					lightEditor.putInt("light5Intensity", light5.getProgress());
					lightEditor.apply();
					light5.setProgress(0);
				}
			}
			
		});
		
		light5.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) 
			{
				lightingPopUp(light5).show();			
				return false;
			}
		});	
	}
	
	private AlertDialog lightingPopUp(final ProgressBar light)
	{
		final SharedPreferences lightPref = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor lightEditor = lightPref.edit();
		
		AlertDialog.Builder lightingPopUpBuilder = new AlertDialog.Builder(this);
		lightingPopUpBuilder.setTitle("Light Intensity");
		lightingPopUpBuilder.setIcon(R.drawable.light_setting_icon);
		
		View lightPopUpView=getLayoutInflater().inflate(R.layout.dialog_lighting_pop_up, null);		

		final SeekArc s=(SeekArc) lightPopUpView.findViewById(R.id.seekArc);
	    final TextView lightIntensity=(TextView) lightPopUpView.findViewById(R.id.progress_text);
	    s.setProgress(light.getProgress());
	    lightIntensity.setText(String.valueOf(light.getProgress()) + "%");
	    s.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekArc seekArc) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekArc seekArc) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) 
			{
				lightIntensity.setText(String.valueOf(progress) + "%");
				lightIntensity.setGravity(Gravity.CENTER);
			}
		});
	    
	    if(light.getTag()=="R.drawable.light_button_bg")	    	
	    {
	    	s.setProgress(0);
	    }
	   	   	    
	    lightingPopUpBuilder.setView(lightPopUpView);
	    
	    lightingPopUpBuilder.setPositiveButton("Apply", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
				lightEditor.putInt(getResources().getResourceEntryName(light.getId())+"Intensity", s.getProgress());
				if(s.getProgress()==0)
				{
					lightEditor.putBoolean(getResources().getResourceEntryName(light.getId())+"On", false);
				}
				else
				{
					lightEditor.putBoolean(getResources().getResourceEntryName(light.getId())+"On", true);
				}
				
				lightEditor.apply();
				light.setProgress(s.getProgress());				
			}
	    	
	    });
	    
		lightingPopUpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				dialog.cancel();				
			}
			
		});
		
		AlertDialog lightingPopUp=lightingPopUpBuilder.create();
		return lightingPopUp;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.lighting_manager, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		if(item.getItemId()==R.id.scan)
		{
			final SharedPreferences lightPref = PreferenceManager.getDefaultSharedPreferences(this);
			final SharedPreferences.Editor lightEditor = lightPref.edit();
			LightingManagerScanDialog scanDialog=new LightingManagerScanDialog(this);
			scanDialog.setTitle("Scan");
			scanDialog.show();

			//Simulate new floor plan found
			lightEditor.clear();
			final ProgressBar light1=(ProgressBar) findViewById(R.id.light1);
			light1.setProgress(0);
			final ProgressBar light2=(ProgressBar) findViewById(R.id.light2);
			light2.setProgress(0);
			final ProgressBar light3=(ProgressBar) findViewById(R.id.light3);
			light3.setProgress(0);
			final ProgressBar light4=(ProgressBar) findViewById(R.id.light4);
			light4.setProgress(0);
			final ProgressBar light5=(ProgressBar) findViewById(R.id.light5);
			light5.setProgress(0);
			return true;
		}
		else if(item.getItemId()==R.id.auto)
		{
			if(item.isChecked())
			{		
				item.setChecked(false);
			}
			else
			{
				final SharedPreferences lightPref = PreferenceManager.getDefaultSharedPreferences(this);
				final SharedPreferences.Editor lightEditor = lightPref.edit();
				for(int i=0; i<lightAuto.size(); i++)
				{
					lightEditor.putBoolean("light" + String.valueOf(i+1) +"On", true);
					lightEditor.putInt("light" + String.valueOf(i+1) +"Intensity", lightAuto.get(i));
					int id=0;
					switch (i)
					{
						case 0: id=R.id.light1; break;
						case 1: id=R.id.light2; break;
						case 2: id=R.id.light3; break;
						case 3: id=R.id.light4;	break;
						case 4: id=R.id.light5; break;
					}
					ProgressBar light=(ProgressBar) findViewById(id);
					light.setProgress(lightAuto.get(i));
				}
				lightEditor.apply();
				item.setChecked(true);
			}
			return true;
		}
		else if(item.getItemId()==R.id.help)
		{
			LightingManagerHelpDialog helpDialog=new LightingManagerHelpDialog(this);
			helpDialog.show();
			return true;
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}
	}
}
