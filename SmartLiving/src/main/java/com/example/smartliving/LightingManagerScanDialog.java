package com.example.smartliving;

import android.app.*;
import android.content.Context;
import android.os.*;
import android.widget.*;

public class LightingManagerScanDialog extends Dialog
{
	Runnable updater;
	final Handler timerHandler = new Handler();
	public LightingManagerScanDialog(Context context) 
	{
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_lighting_scan);
		updateTime();
	}
	
	@Override
	public void dismiss()
	{
		super.dismiss();
		timerHandler.removeCallbacks(updater);
	}
	
	private void updateTime() 
	{
	    final ProgressBar bar=(ProgressBar) findViewById(R.id.scan_progress);
	    final TextView text= (TextView) findViewById(R.id.text);
	    updater = new Runnable() {
	        private int i=0;
	    	@Override
	        public void run() {	            
	        	if(i<=100)
	        	{
	        		text.setText(String.valueOf(i) + "%");
		    		bar.setProgress(i);
		            timerHandler.postDelayed(updater,100);
		            i++;
	        	}
	        	else
	        	{
	        		dismiss();
	        	}
	        }
	    };
	    timerHandler.post(updater);
	}
}
