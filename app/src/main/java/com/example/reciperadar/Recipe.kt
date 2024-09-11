package com.example.reciperadar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
class Recipe (var IMG:String, var TITLE:String, var CATEGORY:String, var COOKING_TIME:String,
              var DESCRIPTION:String, var INGREDIENTS:String, var DIRECTIONS:String, var FEATURED:Int) {

    @JvmField
    @PrimaryKey(autoGenerate = true)
    var ID = 0
}