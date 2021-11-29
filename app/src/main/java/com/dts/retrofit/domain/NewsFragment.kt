package com.dts.retrofit.domain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.dts.retrofit.R
import com.dts.retrofit.adapter.NewsAdapter
import com.dts.retrofit.data.impl.NewsRepositoryImpl
import com.dts.retrofit.databinding.UaNewsFragmentBinding
import com.dts.retrofit.detail.NewsDetailActivity
import com.dts.retrofit.model.NewsModel
import timber.log.Timber

class NewsFragment(private val sourceUrl: String, private val pathUrl: String) :
    Fragment(R.layout.ua_news_fragment), OnLoadNewsCallback {

    companion object {
        fun create(link: String): NewsFragment {
            val uri = link.toUri()

            val updatedUrl = Uri.Builder()
                .scheme(uri.scheme)
                .authority(uri.authority)
                .build()
            val host = updatedUrl.toString()
            val path = link.toUri().path.orEmpty()
            return NewsFragment(host, path)
        }
    }

    private val adapter = NewsAdapter()
    private lateinit var binding: UaNewsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UaNewsFragmentBinding.bind(
            super.onCreateView(
                inflater,
                container,
                savedInstanceState
            )!!
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        NewsRepositoryImpl(requireContext()).fetchNews(this, sourceUrl, pathUrl)
    }


    private fun setupAdapter() {
        Timber.d("Recycler is invited")
        binding.rvNews.adapter = adapter
        adapter.setOnItemClickListener { news ->
            Timber.i("click:$news")
            val intent = Intent(requireContext(), NewsDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("news_item", news.data)

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onLoad(items: List<NewsModel>) {
        Timber.d("loaded data : $items")
        adapter.addNews(items)

    }

    override fun onFail(message: String) {
        Timber.d("UA Fail $message")
    }

    fun getTitle() = "Tab: ${sourceUrl.toUri().host}"

}