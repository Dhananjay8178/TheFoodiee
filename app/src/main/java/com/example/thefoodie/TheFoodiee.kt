package com.example.thefoodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class TheFoodiee : AppCompatActivity(), RecipeItemClicked {

    private lateinit var mAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_foodiee)

         var recipe = intent.getStringExtra("recipe")



        RecyclerView.LayoutManager = LinearLayoutManager(this)

         fetchdata()
         mAdapter = RecipeListAdapter(this)
        RecyclerView.adapter = mAdapter

    }

    private fun fetchdata() {

        val url = "https://api.spoonacular.com/recipes/716429/information?apiKey=04505c61e4ce40d6bd564d8a6a2b2049&includeNutrition=true."

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener {
                  val recipeJsonArray = it.getJSONArray("results")
                    val recipeArray = ArrayList<Recipe>()
                    for(i in 0 until recipeJsonArray.length()){
                        val recipeJsonObject = recipeJsonArray.getJSONObject(i)
                        val recipe = Recipe(
                                recipeJsonObject.getString("title"),
                                recipeJsonObject.getString("calories"),
                                recipeJsonObject.getString("carbs"),
                                recipeJsonObject.getString("fat"),
                                recipeJsonObject.getString("proteins"),
                                recipeJsonObject.getString("image"),

                        )
                        recipeArray.add(recipe)
                    }
                    mAdapter.updateRecipe(recipeArray)
                },
                Response.ErrorListener {

                }
        )

// Access the RequestQueue through your singleton class.
        
        MySingelton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }




    override fun onItemClicked(item: Recipe) {

    }


}