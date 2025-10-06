package com.example.e_commerce_app.Activity

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e_commerce_app.Helper.ManagmentCart
import com.example.e_commerce_app.databinding.ActivityDetailBinding
import com.example.e_commerce_app.model.ItemModel

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var item: ItemModel

    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart= ManagmentCart(this)
        item=intent.getSerializableExtra("object")!! as ItemModel

        setupView()
    }

    private fun setupView()=with(binding) {
        titleTxt.text=item.title
        descriptionTxt.text=item.description
        priceTxt.text="$${item.price}"
        oldpriceTxt.text="$${item.oldPrice}"
        oldpriceTxt.paintFlags=priceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        ratingTxt.text="${item.rating} Rating"
        numberitemTxt.text=item.numberInCart.toString()
        updateTotalPrice()
        Glide.with(this@DetailActivity)
            .load(item.picURL.firstOrNull())
            .into(picMain)

        backBtn.setOnClickListener { finish() }

        plusBtn.setOnClickListener {
            item.numberInCart--
            numberitemTxt.text=item.numberInCart.toString()
            updateTotalPrice()
        }

        minusBtn.setOnClickListener {
            if (item.numberInCart > 1) {
                item.numberInCart++
                numberitemTxt.text = item.numberInCart.toString()
                updateTotalPrice()
            }
        }

     addtoCartBtn.setOnClickListener {
         managmentCart.insert(item)
     }
    }

    private fun updateTotalPrice()=with(binding){
       totalPriceTxt.text="$${item.price * item.numberInCart}"
    }
}