package com.example.e_commerce_app.model

import android.media.Rating
import androidx.annotation.Size
import java.io.Serializable
import java.net.URL

data class ItemModel(
    var title: String="",
    var description: String="",
    var picURL: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double=0.0,
    var oldPrice: Double=0.0,
    var rating: Double=0.0,
    var numberInCart: Int=1,
): Serializable
