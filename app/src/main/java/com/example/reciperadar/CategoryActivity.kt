package com.example.reciperadar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciperadar.databinding.ActivityCategoryBinding
import com.example.reciperadar.databinding.ActivityHomeBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter:CategoryAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = intent.getStringExtra("TITLE")
        setUpRecyclerView()
        binding.backButton2.setOnClickListener{
            finish()
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.categorySearchTitle.text = intent.getStringExtra("CATEGORY")
        binding.categorySearchRecycler.layoutManager = LinearLayoutManager(this)

        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject = db.getDao()
        var recipes = daoObject.getAllRecipes()

        for (i in recipes.indices){
            if (recipes[i].CATEGORY.contains(intent.getStringExtra("CATEGORY")!!)){
                dataList.add(recipes[i])
            }
            rvAdapter = CategoryAdapter(dataList, this)
            binding.categorySearchRecycler.adapter = rvAdapter
        }
    }
}