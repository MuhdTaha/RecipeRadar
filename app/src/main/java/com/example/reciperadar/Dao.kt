package com.example.reciperadar

import androidx.room.Dao
import androidx.room.Query
@Dao
interface Dao {
    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes(): List<Recipe>
}