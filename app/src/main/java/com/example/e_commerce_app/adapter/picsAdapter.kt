package com.example.e_commerce_app.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_app.databinding.ActivityMainBinding
import com.example.e_commerce_app.databinding.ViewholderPicsBinding

class picsAdapter(val items: MutableList<String>,private val onImageSelected:(String)-> Unit):
RecyclerView.Adapter<picsAdapter.Viewholder>()
{
    inner class Viewholder(val binding: ViewholderPicsBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): picsAdapter.Viewholder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: picsAdapter.Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}