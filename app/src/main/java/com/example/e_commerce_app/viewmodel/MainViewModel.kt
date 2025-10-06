package com.example.e_commerce_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce_app.model.BrandModel
import com.example.e_commerce_app.model.ItemModel
import com.example.e_commerce_app.model.SliderModel
import com.example.e_commerce_app.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()
    val brands: LiveData<MutableList<BrandModel>> = repository.brands
    val banners: LiveData<List<SliderModel>> = repository.banners
    val popular: LiveData<MutableList<ItemModel>> = repository.popular

    fun loadBrands() = repository.loadBrands()
    fun loadBanners() = repository.loadBanners()
    fun loadPopular() = repository.loadPopular()

}