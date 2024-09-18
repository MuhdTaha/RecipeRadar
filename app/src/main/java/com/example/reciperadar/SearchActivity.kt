package com.example.reciperadar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciperadar.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchBinding
    private lateinit var rvAdapter:SearchAdapter
    private lateinit var dataList:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecipes.requestFocus()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.recipesSearchRecycler.layoutManager = LinearLayoutManager(this)

        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
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
            rvAdapter = SearchAdapter(dataList, this)
            binding.recipesSearchRecycler.adapter = rvAdapter
        }
    }
}