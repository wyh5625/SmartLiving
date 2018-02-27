package com.example.smartliving;

import java.util.*;
import android.app.*;
import android.content.Context;
import android.database.MatrixCursor;
import android.graphics.*;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.SearchView.*;
import android.widget.TabHost.TabSpec;

public class RecipeActivity extends Activity
{
	protected static ArrayList<Recipe> recipeList;
	protected SimpleCursorAdapter mAdapter;
	protected ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);

		findRecipe(recipeList.get(MainActivity.recipeIdx).name);

		final String[] from = new String[] {"recipe"};
        final int[] to = new int[] {android.R.id.text1};

		mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        final float scale = getResources().getDisplayMetrics().density;
		float w = displayMetrics.widthPixels;

		final ImageView recipeImage=(ImageView) findViewById(R.id.recipe_image);
		recipeImage.getLayoutParams().height=(int) Math.round(w*0.5);
		recipeImage.getLayoutParams().width=(int) Math.round(w*0.5);


		scrollView=(ScrollView) findViewById(R.id.scrollView);
        scrollView.smoothScrollTo(0,0);

		TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec nutrition = tabHost.newTabSpec("tag1");
		nutrition.setContent(R.id.nutrition_list);
		nutrition.setIndicator("Nutrition");
		tabHost.addTab(nutrition);

		TabSpec ingredients = tabHost.newTabSpec("tag2");
		ingredients.setContent(R.id.ingredients);
		ingredients.setIndicator("ingredients");
		tabHost.addTab(ingredients);

		TabSpec methods = tabHost.newTabSpec("tag3");
		methods.setContent(R.id.method);
		methods.setIndicator("Method");
		tabHost.addTab(methods);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.recipe, menu);

		MenuItem searchViewItem = menu.findItem(R.id.search);
		final SearchView searchBar=(SearchView) searchViewItem.getActionView();
		LinearLayout baseLayout=(LinearLayout) findViewById(R.id.layout);
		baseLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if(!searchBar.isIconified())
				{
                    searchBar.onActionViewCollapsed();
				}
				return false;
			}
		});
	    return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		super.onPrepareOptionsMenu(menu);
	    MenuItem searchViewItem = menu.findItem(R.id.search);
	    final SearchView searchBar=(SearchView) searchViewItem.getActionView();
	    searchBar.setQueryHint("Search more...");
	    searchBar.setSuggestionsAdapter(mAdapter);
	    int autoCompleteTextViewID = getResources().getIdentifier("android:id/search_src_text", null, null);
	    AutoCompleteTextView searchAutoCompleteTextView = (AutoCompleteTextView) searchBar.findViewById(autoCompleteTextViewID);
	    searchAutoCompleteTextView.setThreshold(1);
	    searchBar.setOnQueryTextListener(new OnQueryTextListener(){

			@Override
			public boolean onQueryTextSubmit(String query)
			{
				if(findRecipe(query))
				{
                    searchBar.onActionViewCollapsed();
                }
				else
				{
					((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
					final Dialog searchNotFoundDialog=new Dialog(RecipeActivity.this);
					TextView searchNotFound=new TextView(RecipeActivity.this);
					searchNotFound.setText("Please click \"View all Recipes\" to find more");
					searchNotFound.setTextSize(18);
					searchNotFoundDialog.setTitle("Recipe " + "\"" + query + "\"" + " is not found");
					searchNotFoundDialog.setContentView(searchNotFound);
					searchNotFoundDialog.show();
				}
                return false;
			}

			@Override
			public boolean onQueryTextChange(String newText)
			{
				populateAdapter(newText);
				return false;
			}
	    });

	    searchBar.setOnSuggestionListener(new OnSuggestionListener(){

			@Override
			public boolean onSuggestionSelect(int position) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onSuggestionClick(int position)
			{

                searchBar.setQuery(mAdapter.getCursor().getString(1), true);
				return true;
			}

	    });
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if(item.getItemId() == R.id.view_all)
		{
			final Dialog viewAllRecipe=new Dialog(this);
			final ListView listView=new ListView(this);
			String[] recipes=new String[5];
			for(int i=0; i<recipeList.size(); i++)
			{
				recipes[i]=recipeList.get(i).name;
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, recipes);
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					String name=(String) listView.getItemAtPosition(position);
					findRecipe(name);
					viewAllRecipe.dismiss();
				}
			});
			viewAllRecipe.setTitle("View All Recipes");
			viewAllRecipe.addContentView(listView,new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams .MATCH_PARENT));
			viewAllRecipe.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	private void populateAdapter(String query)
	{
		final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "recipe" });
        for (int i=0; i<recipeList.size(); i++)
        {
            if(recipeList.get(i).name.toLowerCase().startsWith(query.toLowerCase()))
            {
        		c.addRow(new Object[] {i, recipeList.get(i).name});
            }
        }
        mAdapter.changeCursor(c);
	}

	protected boolean findRecipe(String name)
	{

        for(int i=0; i<recipeList.size(); i++)
        {
            if(recipeList.get(i).name.equals(name))
			{
				ImageView recipeImage=(ImageView) findViewById(R.id.recipe_image);
                recipeImage.setImageResource(recipeList.get(i).image);

                TextView recipeName=(TextView) findViewById(R.id.recipe_name);
				recipeName.setText(recipeList.get(i).name);

				RatingBar recipeRating=(RatingBar) findViewById(R.id.recipe_rating);
				recipeRating.setRating(recipeList.get(i).rating);
				TextView recipeNumOfrate=(TextView) findViewById(R.id.num_of_rate);
				recipeNumOfrate.setText("(" + String.valueOf(recipeList.get(i).numOfrate) + " ratings)");

				TextView recipePrep=(TextView) findViewById(R.id.prepare_time);
				recipePrep.setText("PREP: " + String.valueOf(recipeList.get(i).prep) + " MINS");
				TextView recipeCook=(TextView) findViewById(R.id.cook_time);
				recipeCook.setText("COOK: " + String.valueOf(recipeList.get(i).cook) + " MINS");
				TextView recipeDiff=(TextView) findViewById(R.id.difficulty);
				recipeDiff.setText(recipeList.get(i).diff);
				TextView recipeServe=(TextView) findViewById(R.id.serve);
				recipeServe.setText("SERVES " + String.valueOf(recipeList.get(i).serve));

				TextView recipeCal=(TextView) findViewById(R.id.cal_value);
				recipeCal.setText(recipeList.get(i).nutrition.get(0) + "k");
				TextView recipeFat=(TextView) findViewById(R.id.fat_value);
				recipeFat.setText(recipeList.get(i).nutrition.get(1));
				TextView recipeSaturatedFat=(TextView) findViewById(R.id.saturated_fat_value);
				recipeSaturatedFat.setText(recipeList.get(i).nutrition.get(2));
				TextView recipeCarbs=(TextView) findViewById(R.id.carbs_value);
				recipeCarbs.setText(recipeList.get(i).nutrition.get(3));
				TextView recipeSugar=(TextView) findViewById(R.id.sugars_value);
				recipeSugar.setText(recipeList.get(i).nutrition.get(4));
				TextView recipeFibre=(TextView) findViewById(R.id.fibre_value);
				recipeFibre.setText(recipeList.get(i).nutrition.get(5));
				TextView recipeProtein=(TextView) findViewById(R.id.protein_value);
				recipeProtein.setText(recipeList.get(i).nutrition.get(6));
				TextView recipeSalt=(TextView) findViewById(R.id.salt_value);
				recipeSalt.setText(recipeList.get(i).nutrition.get(7));


                final float scale = getResources().getDisplayMetrics().density;

				LinearLayout ingredientList=(LinearLayout) findViewById(R.id.ingredients);
                ingredientList.removeAllViewsInLayout();
				for(int j=0; j<recipeList.get(i).ingredients.size(); j++)
				{
					TextView tv=new TextView(this);
					tv.setText(recipeList.get(i).ingredients.get(j));
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams .WRAP_CONTENT,LinearLayout.LayoutParams .WRAP_CONTENT);
					params.setMargins((int) (10*scale + 0.5f), (int) (10*scale + 0.5f), 0, 0); //substitute parameters for left, top, right, bottom
					tv.setLayoutParams(params);
					ingredientList.addView(tv);

					if(j<recipeList.get(i).ingredients.size()-1)
					{
						View divider=new View(this);
						int width = (int) (270*scale + 0.5f);
						int height = (int) (1*scale + 0.5f);
						params=new LinearLayout.LayoutParams (width,height);
						params.gravity=Gravity.CENTER;
						params.setMargins(0, (int) (10*scale + 0.5f), 0, 0);
						divider.setBackgroundColor(Color.GRAY);
						divider.setLayoutParams(params);
						ingredientList.addView(divider);
					}
				}

				LinearLayout methodList=(LinearLayout) findViewById(R.id.method);
                methodList.removeAllViewsInLayout();
				for(int j=0; j<recipeList.get(i).method.size(); j++)
				{
					LinearLayout layout=new LinearLayout(this);
					layout.setOrientation(LinearLayout.HORIZONTAL);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams .WRAP_CONTENT);
					params.setMargins(0, (int) (10*scale + 0.5f), 0, 0); //substitute parameters for left, top, right, bottom
					layout.setLayoutParams(params);

					TextView num=new TextView(this);
					num.setText(String.valueOf(j+1) + ".");
					num.setTextSize(18);
					params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams .WRAP_CONTENT);
					params.setMargins(0, 0, (int) (15*scale + 0.5f), 0); //substitute parameters for left, top, right, bottom
					num.setLayoutParams(params);
					num.setTypeface(num.getTypeface(), Typeface.BOLD);
					layout.addView(num);

					TextView tv=new TextView(this);
					tv.setText(recipeList.get(i).method.get(j));
					layout.addView(tv);

					methodList.addView(layout);
				}
				return true;
			}
		}
		return false;
	}

	protected static void initRecipeList()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe r1=new Recipe();
        r1.image=R.drawable.spanish_meatball_butter_bean_stew;
		r1.name="Spanish Meatball and Butter Bean Stew";
		r1.rating=5;
		r1.numOfrate=48;
		r1.prep=15;
		r1.cook=35;
		r1.diff="EASY";
		r1.serve=3;
		r1.nutrition.add("435");
		r1.nutrition.add("15g");
		r1.nutrition.add("5g");
		r1.nutrition.add("35g");
		r1.nutrition.add("22g");
		r1.nutrition.add("12g");
		r1.nutrition.add("33g");
		r1.nutrition.add("1.3g");
		r1.ingredients.add("350g lean pork mince");
		r1.ingredients.add("1 large red onion, chopped");
		r1.ingredients.add("2 peppers sliced, any colour will do");
		r1.ingredients.add("3 garlic cloves, crushed");
		r1.ingredients.add("1 tbsp sweet smoked paprika");
		r1.ingredients.add("2 x 400g cans chopped tomatoes");
		r1.ingredients.add("400g can butter beans, drained");
		r1.ingredients.add("2 tsp golden caster sugar");
		r1.ingredients.add("small bunch parsley, chopped");
		r1.ingredients.add("crusty bread, to serve (optional)");
		r1.method.add("Season the pork, working the seasoning in with your hands, then shape into small meatballs. Heat the oil in a large pan, add the meatballs and cook for 5 mins, until golden brown all over. Push to one side of the pan and add the onion and peppers. Cook for a further 5 mins, stirring now and then, until the veg has softened, then stir in the garlic and paprika. Stir everything around in the pan for 1 min, then add the tomatoes. Cover with a lid and simmer for 10 mins.");
		r1.method.add("Uncover, stir in the beans, the sugar and some seasoning, then simmer for a further 10 mins, uncovered. Just before serving, stir in the parsley. Serve with crusty bread for dunking, if you like.");
		recipeList.add(r1);

        Recipe r2=new Recipe();
        r2.image=R.drawable.smoky_hake_beans_and_greens;
        r2.name="Smoky Hake, Beans and Greens";
        r2.rating=5;
        r2.numOfrate=25;
        r2.prep=15;
        r2.cook=10;
        r2.diff="EASY";
        r2.serve=2;
        r2.nutrition.add("554");
        r2.nutrition.add("27g");
        r2.nutrition.add("7g");
        r2.nutrition.add("28g");
        r2.nutrition.add("9g");
        r2.nutrition.add("6g");
        r2.nutrition.add("45g");
        r2.nutrition.add("2.2g");
        r2.ingredients.add("mild olive oil");
        r2.ingredients.add("½ x 200g pack raw cooking chorizo (we used Unearthed Alfresco Smoked)");
        r2.ingredients.add("1 onion, finely chopped");
        r2.ingredients.add("260g bag spinach");
        r2.ingredients.add("2 x 140g skinless hake fillets");
        r2.ingredients.add("½ tsp sweet smoked paprika");
        r2.ingredients.add("1 red chilli, deseeded and shredded");
        r2.ingredients.add("400g can cannellini beans, drained");
        r2.ingredients.add("juice ½ lemon");
        r2.ingredients.add("1 tbsp extra virgin olive oil");
        r2.method.add("Boil a full kettle of water and heat the grill to high. Heat 1 tsp oil in a large frying pan. Squeeze the meat from the chorizo directly into the pan. Add the onion and fry for 5 mins, crushing the meat with a spatula until broken up, golden and surrounded by its juices. The onion will also be soft and golden.");
        r2.method.add("Meanwhile, put the spinach in a colander, slowly pour over the boiled water to wilt it, then run under the cold tap. Squeeze out the excess water using your hands, then set aside. Line a baking tray with foil, rub with a little oil and place the fish on top. Season, sprinkle over the smoked paprika and drizzle with a little more oil.");
        r2.method.add("ip the chilli into the pan with the sausages, fry for 1 min more, then add the beans, spinach, lemon juice and extra virgin olive oil. Let it warm through gently, then season to taste.");
        r2.method.add("Grill the fish for 5 mins or until flaky but not dry – you won’t need to turn it. Spoon the bean mixture onto plates, then carefully top with the fish and any juices from the tray. Serve with a dollop of Quick garlic mayonnaise (see recipe, right), if you like.");
        recipeList.add(r2);

        Recipe r3=new Recipe();
        r3.image=R.drawable.minty_roast_veg_and_houmous_salad;
        r3.name="Minty Roast Veg and Houmous Salad";
        r3.rating=5;
        r3.numOfrate=11;
        r3.prep=15;
        r3.cook=40;
        r3.diff="EASY";
        r3.serve=4;
        r3.nutrition.add("611");
        r3.nutrition.add("26g");
        r3.nutrition.add("9g");
        r3.nutrition.add("61g");
        r3.nutrition.add("36g");
        r3.nutrition.add("20g");
        r3.nutrition.add("23g");
        r3.nutrition.add("3.5g");
        r3.ingredients.add("4 parsnips, peeled and cut into wedges");
        r3.ingredients.add("4 carrots, cut into wedges");
        r3.ingredients.add("2 tsp cumin seeds");
        r3.ingredients.add("400g can chickpeas, drained");
        r3.ingredients.add("2 tbsp vegetable oil");
        r3.ingredients.add("500g pack cooked beetroot (not in vinegar), drained and cut into wedges");
        r3.ingredients.add("2 tbsp clear honey");
        r3.ingredients.add("200g pot houmous");
        r3.ingredients.add("2 tbsp white wine vinegar");
        r3.ingredients.add("small bunch mint, leaves picked");
        r3.ingredients.add("200g block Greek-style salad cheese or feta");
        r3.method.add("Heat oven to 200C/180C fan/gas 6. Toss the parsnips, carrots, cumin seeds and chickpeas with the oil and some seasoning in a large roasting tin. Cook for 30 mins, tossing halfway through cooking.");
        r3.method.add("Add the beetroot to the tin and drizzle over the honey, then return to the oven for 10 mins. Spread the houmous thinly over a large platter, or divide between 4 dinner plates. When the veg is ready, drizzle with the vinegar and toss together in the tin. Tip the roasted vegetables on top of the houmous, scatter over the mint and cheese, drizzle with any juices from the tin and serve.");
        recipeList.add(r3);

		Recipe r4=new Recipe();
		r4.image=R.drawable.mushroom_fajitas_with_avocado_houmous;
		r4.name="Mushroom Fajitas with Avocado Houmous";
		r4.rating=4;
		r4.numOfrate=10;
		r4.prep=15;
		r4.cook=20;
		r4.diff="EASY";
		r4.serve=2;
		r4.nutrition.add("824");
		r4.nutrition.add("36g");
		r4.nutrition.add("6g");
		r4.nutrition.add("104g");
		r4.nutrition.add("11g");
		r4.nutrition.add("14g");
		r4.nutrition.add("23g");
		r4.nutrition.add("2.3g");
		r4.ingredients.add("1 large avocado, stoned, peeled and chopped");
		r4.ingredients.add("400g can chickpea, drained and rinsed");
		r4.ingredients.add("1 garlic clove, crushed");
		r4.ingredients.add("zest and juice 1 lemon");
		r4.ingredients.add("2 tomatoes, deseeded and diced");
		r4.ingredients.add("1 red onion, cut into thick rounds");
		r4.ingredients.add("2 large flat mushrooms thickly sliced");
		r4.ingredients.add("2 tbsp olive oil");
		r4.ingredients.add("2 tsp fajita spice mix");
		r4.ingredients.add("4 tortillas");
		r4.ingredients.add("shredded Little Gem lettuce and Tabasco sauce, to serve (optional)");
		r4.method.add("Put the avocado, chickpeas, garlic, lemon zest and juice in a food processor and whizz together until it forms a chunky consistency. Spoon into a bowl, season and stir in the tomatoes.");
		r4.method.add("Drizzle the onion and mushroom with the oil and sprinkle over the fajita seasoning. Heat a griddle pan over a high heat and cook the onion for 2 mins on each side, then remove from the pan and keep warm. Cook the mushrooms for 2 mins on each side or until softened and turning golden in places.");
		r4.method.add("Spread some of the avocado houmous down the middle of each wrap and top with the mushrooms and onions. Add shredded lettuce and a dash of Tabasco, if you like, and wrap up.");
		recipeList.add(r4);

		Recipe r5=new Recipe();
		r5.image=R.drawable.chickpea_patties_with_carrot_and_raisin_salad;
		r5.name="Chickpea patties with carrot & raisin salad";
		r5.rating=4;
		r5.numOfrate=6;
		r5.prep=10;
		r5.cook=10;
		r5.diff="EASY";
		r5.serve=2;
		r5.nutrition.add("316");
		r5.nutrition.add("14g");
		r5.nutrition.add("2g");
		r5.nutrition.add("31g");
		r5.nutrition.add("12g");
		r5.nutrition.add("9g");
		r5.nutrition.add("17g");
		r5.nutrition.add("1g");
		r5.ingredients.add("400g can chickpea, drained");
		r5.ingredients.add("1 garlic clove, chopped");
		r5.ingredients.add("1 large egg");
		r5.ingredients.add("1 tbsp ground almond");
		r5.ingredients.add("2 tsp harissa");
		r5.ingredients.add("1 tsp ground cumin");
		r5.ingredients.add("3 tbsp chopped parsley");
		r5.ingredients.add("2 tsp rapeseed oil, for frying");
		r5.ingredients.add("1 tbsp raisins");
		r5.ingredients.add("1 carrot, shaved into ribbons with a peeler");
		r5.ingredients.add("1 courgett, shaved into ribbons with a peeler");
		r5.ingredients.add("5 radishes, thinly sliced");
		r5.ingredients.add("2 handfuls from a bag of watercress, rocket & spinach");
		r5.ingredients.add("1 tsp each hemp or rapeseed oil and white wine vinegar, or lemon wedges (optional)");
		r5.method.add("Tip the chickpeas, garlic, egg, almonds, harissa and cumin into a bowl and blitz with a hand blender until smooth. Stir in the parsley. Heat the oil in a non-stick frying pan and dollop in the mixture in 8 big spoonfuls, spaced apart. Cook for 5 mins on each side.");
		r5.method.add("For the salad, toss the raisins and all the veg together, but don’t dress with the oil and vinegar until you are about to eat. If taking to work, add a wedge of lemon to squeeze over instead of the dressing.");
		recipeList.add(r5);
	}
}

class Recipe
{
	protected int image;
	protected String name;
	protected int rating;
	protected int numOfrate;
	protected int prep,cook;
	protected String diff;
	protected int serve;
	protected ArrayList<String> nutrition;
	protected ArrayList<String> ingredients;
	protected ArrayList<String> method;

	public Recipe()
	{
		nutrition=new ArrayList<String>();
		ingredients=new ArrayList<String>();
		method=new ArrayList<String>();
	}
}
