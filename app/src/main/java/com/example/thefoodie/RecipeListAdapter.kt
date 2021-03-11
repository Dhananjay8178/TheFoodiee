package com.example.thefoodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeListAdapter(private val listener: RecipeItemClicked ): RecyclerView.Adapter<RecipeviewHolder>() {

    private val items: ArrayList<Recipe> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeviewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        val viewHolder = RecipeviewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecipeviewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.image)
        holder.calories.text = currentItem.calories
        holder.carbs.text = currentItem.carbs
        holder.fats.text = currentItem.fat
        holder.proteins.text = currentItem.proteins

    }
    fun updateRecipe(updatedRecipe:ArrayList<Recipe>) {
        items.clear()
        items.addAll(updatedRecipe)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return items.size
    }
}

class RecipeviewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val carbs: TextView = itemView.findViewById(R.id.carbs)
    val calories: TextView = itemView.findViewById(R.id.calories)
    val fats: TextView = itemView.findViewById(R.id.fats)
    val proteins: TextView = itemView.findViewById(R.id.proteins)
}


interface RecipeItemClicked{
    fun onItemClicked(item: Recipe)
}