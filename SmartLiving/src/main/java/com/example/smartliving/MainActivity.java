package com.example.smartliving;

import com.example.william.myapplication.*;
import com.example.william.powerconsumption.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity {

	protected static int recipeIdx=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecipeActivity.initRecipeList();
		recipeIdx=ThreadLocalRandom.current().nextInt(0, RecipeActivity.recipeList.size()+1);
		setRecipeWidget();

		Button lightingManagerButton=(Button) findViewById(R.id.app1);
		lightingManagerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), LightingManagerActivity.class);
				startActivity(i);
			}
		});

		Button humidityControlButton=(Button) findViewById(R.id.app2);
		humidityControlButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), HumidityControlMainActivity.class);
				startActivity(i);
			}
		});

		Button recipeButton=(Button) findViewById(R.id.recipe_button);
		recipeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), RecipeActivity.class);
				startActivity(i);
			}
		});

		Button reportButton=(Button) findViewById(R.id.report_button);
		reportButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), ReportMainActivity.class);
				startActivity(i);
			}
		});
	}

	private void setRecipeWidget()
	{
		ImageView recipeImage=(ImageView) findViewById(R.id.recipe_image);
		recipeImage.setImageResource(RecipeActivity.recipeList.get(recipeIdx).image);

		TextView recipeName=(TextView) findViewById(R.id.recipe_name);
		recipeName.setText(RecipeActivity.recipeList.get(recipeIdx).name);

		RatingBar recipeRating=(RatingBar) findViewById(R.id.recipe_rating);
		recipeRating.setRating(RecipeActivity.recipeList.get(recipeIdx).rating);
		TextView recipeNumOfrate=(TextView) findViewById(R.id.num_of_rate);
		recipeNumOfrate.setText("(" + String.valueOf(RecipeActivity.recipeList.get(recipeIdx).numOfrate) + " ratings)");


	}
}
