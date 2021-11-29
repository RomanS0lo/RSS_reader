package com.dts.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dts.retrofit.adapter.PageAdapter
import com.dts.retrofit.databinding.ActivityMainBinding
import com.dts.retrofit.domain.ModalBottomSheet
import com.dts.retrofit.domain.NewsFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    private val adapter = PageAdapter(supportFragmentManager)
    private lateinit var binding: ActivityMainBinding
    private val modalBottomSheet = ModalBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupListener()
        adapter.add(NewsFragment.create("https://www.pravda.com.ua/rss/view_pubs/"))
        modalBottomSheet.onAddNewRss = { fragment ->
            adapter.add(fragment)
        }
        Timber.d("App IS Started")
    }

    private fun setupListener() {
        binding.btnDialogUp.setOnClickListener {
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }
    }

    private fun setupAdapter() {
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
