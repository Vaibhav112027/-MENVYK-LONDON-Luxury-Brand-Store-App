package com.example.e_commerce_app.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.e_commerce_app.adapter.BrandAdapter
import com.example.e_commerce_app.adapter.PopularAdapter
import com.example.e_commerce_app.adapter.SliderAdapter
import com.example.e_commerce_app.databinding.ActivityMainBinding
import com.example.e_commerce_app.model.SliderModel
import com.example.e_commerce_app.viewmodel.MainViewModel

class DashBoardActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    private val brandsAdapter = BrandAdapter(items = mutableListOf())
    private val popularAdapter = PopularAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initBrands()
        initBanner()
        initRecommended()
    }

    private fun initRecommended() {
        binding.recyclerViewRecommendation.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewRecommendation.adapter = popularAdapter
        binding.progressBarRecommendation.visibility = View.VISIBLE

        viewModel.popular.observe(this) { data ->
            popularAdapter.updateData(data)
            binding.progressBarRecommendation.visibility = View.GONE
        }

        viewModel.loadPopular()
    }

    private fun initBrands() {
        binding.recyclerviewBrands.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewBrands.adapter = brandsAdapter
        binding.progressBarBrands.visibility = View.VISIBLE

        viewModel.brands.observe(this) { data ->
            brandsAdapter.updateData(data)
            binding.progressBarBrands.visibility = View.GONE
        }

        viewModel.loadBrands()
    }

    private fun setupBanners(images: List<SliderModel>) {
        binding.viewpagerslider.apply {
            adapter = SliderAdapter(images, this)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            (getChildAt(0) as? RecyclerView)?.overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER

            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            })
        }

        binding.dotIndicator.apply {
            visibility = if (images.size > 1) View.VISIBLE else View.GONE
            if (images.size > 1) attachTo(binding.viewpagerslider)
        }
    }

    private fun initBanner() {
        binding.progressBar2.visibility = View.VISIBLE

        viewModel.banners.observe(this) { items ->
            setupBanners(items)
            binding.progressBar2.visibility = View.GONE
        }

        viewModel.loadBanners()
    }
}