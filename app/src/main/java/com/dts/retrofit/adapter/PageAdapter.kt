package com.dts.retrofit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dts.retrofit.domain.NewsFragment

class PageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val pages = mutableListOf<NewsFragment>()

    fun add(newsFragment: NewsFragment) {
        pages.add(newsFragment)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].getTitle()
    }
}
