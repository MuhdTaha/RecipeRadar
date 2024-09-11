package com.example.reciperadar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reciperadar.databinding.FeaturedRecipesItemsBinding

class FeaturedAdapter(var dataList:ArrayList<Recipe>, var context:Context):RecyclerView.Adapter<FeaturedAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: FeaturedRecipesItemsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = FeaturedRecipesItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set the featured recipe image, title and cooking time
        Glide.with(context)
            .load(dataList[position].IMG)
            .into(holder.binding.featuredImage)

        holder.binding.featuredImageTxt.text = dataList.get(position).TITLE
        holder.binding.featuredImageTime.text = "\uD83D\uDD52  " + dataList.get(position).COOKING_TIME
    }
}