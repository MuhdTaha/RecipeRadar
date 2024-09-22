package com.example.reciperadar

import android.content.Context
import android.content.Intent
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
        holder.itemView.setOnClickListener{
            var intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("IMG", dataList[position].IMG)
            intent.putExtra("TITLE", dataList.get(position).TITLE)
            intent.putExtra("TIME", dataList.get(position).COOKING_TIME)
            intent.putExtra("INGREDIENTS", dataList.get(position).INGREDIENTS)
            intent.putExtra("DIRECTIONS", dataList.get(position).DIRECTIONS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(intent)
        }
    }
}