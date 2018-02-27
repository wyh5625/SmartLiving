package com.example.smartliving;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class RecipeFullListActivity extends Activity
{
    protected ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_full_list);

        recipeList=new ArrayList<Recipe>();
        initRecipeList();

        final ListView recipe=(ListView) findViewById(R.id.list);

        String[] recipes=new String[3];
        for(int i=0; i<recipeList.size(); i++)
        {
            recipes[i]=recipeList.get(i).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, recipes);
        recipe.setAdapter(adapter);

        recipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String name=(String) recipe.getItemAtPosition(position);

            }
        });
    }
    private void initRecipeList()
    {
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
}
