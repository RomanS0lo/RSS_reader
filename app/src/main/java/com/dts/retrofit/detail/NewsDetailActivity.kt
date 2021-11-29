package com.dts.retrofit.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dts.retrofit.data.Item
import com.dts.retrofit.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsItem: Item? = intent?.extras?.getParcelable("news_item")
        if (newsItem != null){
            binding.tvTitle.text = newsItem.title.orEmpty()
            binding.tvDescription.text = newsItem.description.orEmpty()
            binding.wvMoreContent.loadUrl(newsItem.guid.orEmpty())
            binding.btnMore.setOnClickListener {
                binding.wvMoreContent.isVisible = !binding.wvMoreContent.isVisible
            }
        }
    }
}