package com.android.pena.david.bakingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.pena.david.bakingapp.Model.Ingredient;
import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.Utils.AutoValueTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String json = "{\n" +
//                "        \"quantity\": 2,\n" +
//                "        \"measure\": \"CUP\",\n" +
//                "        \"ingredient\": \"Graham Cracker crumbs\"\n" +
//                "      }";
//
//        String json2 = "{\n" +
//                "        \"id\": 0,\n" +
//                "        \"shortDescription\": \"Recipe Introduction\",\n" +
//                "        \"description\": \"Recipe Introduction\",\n" +
//                "        \"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffddf0_-intro-yellow-cake/-intro-yellow-cake.mp4\",\n" +
//                "        \"thumbnailURL\": \"\"\n" +
//                "      }";


        String json2 = "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"name\": \"Nutella Pie\",\n" +
                "\t\"ingredients\": [{\n" +
                "\t\t\t\"quantity\": 2,\n" +
                "\t\t\t\"measure\": \"CUP\",\n" +
                "\t\t\t\"ingredient\": \"Graham Cracker crumbs\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 6,\n" +
                "\t\t\t\"measure\": \"TBLSP\",\n" +
                "\t\t\t\"ingredient\": \"unsalted butter, melted\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 0.5,\n" +
                "\t\t\t\"measure\": \"CUP\",\n" +
                "\t\t\t\"ingredient\": \"granulated sugar\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        String json = "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"name\": \"Nutella Pie\",\n" +
                "\t\"ingredients\": [{\n" +
                "\t\t\t\"quantity\": 2,\n" +
                "\t\t\t\"measure\": \"CUP\",\n" +
                "\t\t\t\"ingredient\": \"Graham Cracker crumbs\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 6,\n" +
                "\t\t\t\"measure\": \"TBLSP\",\n" +
                "\t\t\t\"ingredient\": \"unsalted butter, melted\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 0.5,\n" +
                "\t\t\t\"measure\": \"CUP\",\n" +
                "\t\t\t\"ingredient\": \"granulated sugar\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 1.5,\n" +
                "\t\t\t\"measure\": \"TSP\",\n" +
                "\t\t\t\"ingredient\": \"salt\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 5,\n" +
                "\t\t\t\"measure\": \"TBLSP\",\n" +
                "\t\t\t\"ingredient\": \"vanilla\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 1,\n" +
                "\t\t\t\"measure\": \"K\",\n" +
                "\t\t\t\"ingredient\": \"Nutella or other chocolate-hazelnut spread\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 500,\n" +
                "\t\t\t\"measure\": \"G\",\n" +
                "\t\t\t\"ingredient\": \"Mascapone Cheese(room temperature)\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 1,\n" +
                "\t\t\t\"measure\": \"CUP\",\n" +
                "\t\t\t\"ingredient\": \"heavy cream(cold)\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": 4,\n" +
                "\t\t\t\"measure\": \"OZ\",\n" +
                "\t\t\t\"ingredient\": \"cream cheese(softened)\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"steps\": [{\n" +
                "\t\t\t\"id\": 0,\n" +
                "\t\t\t\"shortDescription\": \"Recipe Introduction\",\n" +
                "\t\t\t\"description\": \"Recipe Introduction\",\n" +
                "\t\t\t\"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"shortDescription\": \"Starting prep\",\n" +
                "\t\t\t\"description\": \"1. Preheat the oven to 350\\u00b0F. Butter a 9\\\" deep dish pie pan.\",\n" +
                "\t\t\t\"videoURL\": \"\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"shortDescription\": \"Prep the cookie crust.\",\n" +
                "\t\t\t\"description\": \"2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.\",\n" +
                "\t\t\t\"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 3,\n" +
                "\t\t\t\"shortDescription\": \"Press the crust into baking form.\",\n" +
                "\t\t\t\"description\": \"3. Press the cookie crumb mixture into the prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.\",\n" +
                "\t\t\t\"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 4,\n" +
                "\t\t\t\"shortDescription\": \"Start filling prep\",\n" +
                "\t\t\t\"description\": \"4. Beat together the nutella, mascarpone, 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or high speed with a hand mixer until fluffy.\",\n" +
                "\t\t\t\"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd97a_1-mix-marscapone-nutella-creampie/1-mix-marscapone-nutella-creampie.mp4\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 5,\n" +
                "\t\t\t \"shortDescription\": \"Finish filling prep\",\n" +
                "\t\t\t\"description\": \"5. Beat the cream cheese and 50 grams (1/4 cup) of sugar on medium speed in a stand mixer or high speed with a hand mixer for 3 minutes. Decrease the speed to medium-low and gradually add in the cold cream. Add in 2 teaspoons of vanilla and beat until stiff peaks form.\",\n" +
                "\t\t\t\"videoURL\": \"\",\n" +
                "\t\t\t\"thumbnailURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda20_7-add-cream-mix-creampie/7-add-cream-mix-creampie.mp4\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 6,\n" +
                "\t\t\t\"shortDescription\": \"Finishing Steps\",\n" +
                "\t\t\t\"description\": \"6. Pour the filling into the prepared crust and smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for at least 2 hours. Then it's ready to serve!\",\n" +
                "\t\t\t\"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda45_9-add-mixed-nutella-to-crust-creampie/9-add-mixed-nutella-to-crust-creampie.mp4\",\n" +
                "\t\t\t\"thumbnailURL\": \"\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"servings\": 8,\n" +
                "\t\"image\": \"\"\n" +
                "}";

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new AutoValueTypeAdapterFactory())
                .create();
     //   Ingredient ing = gson.fromJson(json,Ingredient.class);
     //   Step step = gson.fromJson(json2,Step.class);
        //Recipe recipe = gson.fromJson(json,Recipe.class);


    }


}
