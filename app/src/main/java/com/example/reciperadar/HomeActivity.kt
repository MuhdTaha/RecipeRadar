package com.example.reciperadar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciperadar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter:FeaturedAdapter
    private lateinit var dataList:ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        binding.searchView.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }

        var myIntent = Intent(this@HomeActivity, CategoryActivity::class.java)
        binding.appetizerImage.setOnClickListener{
            myIntent.putExtra("TITLE", "Appetizers")
            myIntent.putExtra("CATEGORY", "Appetizers")
            startActivity(myIntent)
        }
        binding.mainImage.setOnClickListener{
            myIntent.putExtra("TITLE", "Main")
            myIntent.putExtra("CATEGORY", "Main Course")
            startActivity(myIntent)
        }
        binding.dessertImage.setOnClickListener{
            myIntent.putExtra("TITLE", "Dessert")
            myIntent.putExtra("CATEGORY", "Dessert")
            startActivity(myIntent)
        }
        binding.drinksImage.setOnClickListener{
            myIntent.putExtra("TITLE", "Drinks")
            myIntent.putExtra("CATEGORY", "Drinks")
            startActivity(myIntent)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.featuredRecView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()   
            .createFromAsset("recipe.db")
            .build()

        var daoObject = db.getDao()
        var recipes = daoObject.getAllRecipes()

        for (i in recipes.indices){
            if (recipes[i].FEATURED == 1){
                dataList.add(recipes[i])
            }
            rvAdapter = FeaturedAdapter(dataList, this)
            binding.featuredRecView.adapter = rvAdapter
        }
    }
}