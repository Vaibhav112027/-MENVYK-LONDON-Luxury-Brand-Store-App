package com.example.e_commerce_app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerce_app.Activity.DetailActivity
import com.example.e_commerce_app.databinding.ViewholderRecommendationBinding
import com.example.e_commerce_app.model.ItemModel

class PopularAdapter(
    private val items: MutableList<ItemModel>
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    fun updateData(newData: List<ItemModel>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ViewholderRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false // ✅ important: attachToRoot = false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            titleTxt.text = item.title
            price.text = "$${item.price}"
            ratingTxt.text = item.rating.toString()

            Glide.with(holder.itemView.context)
                .load(item.picURL.firstOrNull())
                .apply(RequestOptions().transform(CenterCrop()))
                .into(picture)

            root.setOnClickListener {
                val intent= Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra("object",item)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
