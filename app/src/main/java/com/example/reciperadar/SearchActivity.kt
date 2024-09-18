package com.example.reciperadar

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciperadar.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySearchBinding
    private lateinit var rvAdapter:SearchAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var recipes:List<Recipe>
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecipes.requestFocus()

        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject = db.getDao()
        recipes = daoObject.getAllRecipes()
        setUpRecyclerView()
        binding.backButton.setOnClickListener{
            finish()
        }

        binding.searchRecipes.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "") {
                    filterData(s.toString())
                } else {
                    setUpRecyclerView()
                }
            }   

            override fun afterTextChanged(s: Editable?) {

            }
        })

//        var  inm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodService
//        binding.recipesSearchRecycler.setOnTouchListener(return inm.hideWindow())

    }

    private fun filterData(filterText: String) {
        var filterData = java.util.ArrayList<Recipe>()
        for (i in recipes.indices) {
            if (recipes[i].TITLE.lowercase().contains(filterText.lowercase())) {
                filterData.add(recipes[i])
            }
            rvAdapter.filterList(filterList = filterData)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.recipesSearchRecycler.layoutManager = LinearLayoutManager(this)

        for (i in recipes.indices){
            if (recipes[i].FEATURED == 1){
                dataList.add(recipes[i])
            }
            rvAdapter = SearchAdapter(dataList, this)
            binding.recipesSearchRecycler.adapter = rvAdapter
        }
    }
}