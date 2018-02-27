package com.example.smartliving;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;

public class LightingManagerHelpDialog extends Dialog
{
	public LightingManagerHelpDialog(Context context) 
	{
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_lighting_help);
		
		setTitle("Help");
	}

}
