package com.example.reciperadar

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
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

        binding.navBar.setOnClickListener{
            var dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_sheet)
            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(getColor(R.color.white)))
            dialog.window?.setGravity(Gravity.BOTTOM)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.featuredRecView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()   
            .createFromAsset("recipes.db")
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