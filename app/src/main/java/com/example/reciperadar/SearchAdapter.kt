package com.example.reciperadar

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reciperadar.databinding.SearchRvBinding

class SearchAdapter(var dataList: ArrayList<Recipe>, var context:Context):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding:SearchRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Recipe>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).IMG).into(holder.binding.searchRvImage)
        holder.binding.recipeSearchName.text = dataList.get(position).TITLE
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